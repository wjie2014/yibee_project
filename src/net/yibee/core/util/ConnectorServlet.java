//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.yibee.core.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.fckeditor.handlers.CommandHandler;
import net.fckeditor.handlers.ConnectorHandler;
import net.fckeditor.handlers.ExtensionsHandler;
import net.fckeditor.handlers.RequestCycleHandler;
import net.fckeditor.handlers.ResourceTypeHandler;
import net.fckeditor.response.UploadResponse;
import net.fckeditor.response.XmlResponse;
import net.fckeditor.tool.Utils;
import net.fckeditor.tool.UtilsFile;
import net.fckeditor.tool.UtilsResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectorServlet extends HttpServlet {
    private static final long serialVersionUID = -5742008970929377161L;
    private static final Logger logger = LoggerFactory.getLogger(ConnectorServlet.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public ConnectorServlet() {
    }

    public void init() throws ServletException, IllegalArgumentException {
        String realDefaultUserFilesPath = this.getServletContext().getRealPath(ConnectorHandler.getDefaultUserFilesPath());
        File defaultUserFilesDir = new File(realDefaultUserFilesPath);
        UtilsFile.checkDirAndCreate(defaultUserFilesDir);
        logger.info("ConnectorServlet successfully initialized!");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Entering ConnectorServlet#doGet");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String commandStr = request.getParameter("Command");
        String typeStr = request.getParameter("Type");
        String currentFolderStr = request.getParameter("CurrentFolder");
        currentFolderStr = new String(currentFolderStr.getBytes("iso8859-1"), "utf-8");
        logger.debug("Parameter Command: {}", commandStr);
        logger.debug("Parameter Type: {}", typeStr);
        logger.debug("Parameter CurrentFolder: {}", currentFolderStr);
        XmlResponse xr;
        if(!RequestCycleHandler.isEnabledForFileBrowsing(request)) {
            xr = new XmlResponse(1, "The current user isn\'t authorized for file browsing!");
        } else if(!CommandHandler.isValidForGet(commandStr)) {
            xr = new XmlResponse(1, "Invalid command specified");
        } else if(typeStr != null && !ResourceTypeHandler.isValid(typeStr)) {
            xr = new XmlResponse(1, "Invalid resource type specified");
        } else if(!UtilsFile.isValidPath(currentFolderStr)) {
            xr = new XmlResponse(1, "Invalid current folder specified");
        } else {
            CommandHandler command = CommandHandler.getCommand(commandStr);
            ResourceTypeHandler resourceType = ResourceTypeHandler.getDefaultResourceType(typeStr);
            String typeDirPath = null;
            if("File".equals(typeStr)) {
                typeDirPath = this.getServletContext().getRealPath("WEB-INF/userfiles/");
            } else {
                String typeDir = UtilsFile.constructServerSidePath(request, resourceType);
                typeDirPath = this.getServletContext().getRealPath(typeDir);
            }

            File typeDir1 = new File(typeDirPath);
            UtilsFile.checkDirAndCreate(typeDir1);
            File currentDir = new File(typeDir1, currentFolderStr);
            if(!currentDir.exists()) {
                xr = new XmlResponse(102);
            } else {
                xr = new XmlResponse(command, resourceType, currentFolderStr, UtilsResponse.constructResponseUrl(request, resourceType, currentFolderStr, true, ConnectorHandler.isFullUrl()));
                if(command.equals(CommandHandler.GET_FOLDERS)) {
                    xr.setFolders(currentDir);
                } else if(command.equals(CommandHandler.GET_FOLDERS_AND_FILES)) {
                    xr.setFoldersAndFiles(currentDir);
                } else if(command.equals(CommandHandler.CREATE_FOLDER)) {
                    String tempStr = request.getParameter("NewFolderName");
                    tempStr = new String(tempStr.getBytes("iso8859-1"), "UTF-8");
                    String newFolderStr = UtilsFile.sanitizeFolderName(tempStr);
                    logger.debug("Parameter NewFolderName: {}", newFolderStr);
                    File newFolder = new File(currentDir, newFolderStr);
                    boolean errorNumber = true;
                    int errorNumber1;
                    if(newFolder.exists()) {
                        errorNumber1 = 101;
                    } else {
                        try {
                            errorNumber1 = newFolder.mkdir()?0:102;
                        } catch (SecurityException var18) {
                            errorNumber1 = 103;
                        }
                    }

                    xr.setError(errorNumber1);
                }
            }
        }

        out.print(xr);
        out.flush();
        out.close();
        logger.debug("Exiting ConnectorServlet#doGet");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Entering Connector#doPost");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        String commandStr = request.getParameter("Command");
        String typeStr = request.getParameter("Type");
        String currentFolderStr = request.getParameter("CurrentFolder");
        logger.debug("Parameter Command: {}", commandStr);
        logger.debug("Parameter Type: {}", typeStr);
        logger.debug("Parameter CurrentFolder: {}", currentFolderStr);
        if(Utils.isEmpty(commandStr) && Utils.isEmpty(currentFolderStr)) {
            commandStr = "QuickUpload";
            currentFolderStr = "/";
        }

        UploadResponse ur;
        String[] resourceType;
        if(!RequestCycleHandler.isEnabledForFileUpload(request)) {
            resourceType = new String[]{"203", null, null, "The current user isn\'t authorized for file upload!"};
            ur = new UploadResponse(resourceType);
        } else if(!CommandHandler.isValidForPost(commandStr)) {
            resourceType = new String[]{"1", null, null, "Invalid command specified"};
            ur = new UploadResponse(resourceType);
        } else if(typeStr != null && !ResourceTypeHandler.isValid(typeStr)) {
            resourceType = new String[]{"1", null, null, "Invalid resource type specified"};
            ur = new UploadResponse(resourceType);
        } else if(!UtilsFile.isValidPath(currentFolderStr)) {
            ur = UploadResponse.UR_INVALID_CURRENT_FOLDER;
        } else {
            ResourceTypeHandler var25 = ResourceTypeHandler.getDefaultResourceType(typeStr);
            String typeDirPath = null;
            if("File".equals(typeStr)) {
                typeDirPath = this.getServletContext().getRealPath("WEB-INF/userfiles/");
            } else {
                String typeDir = UtilsFile.constructServerSidePath(request, var25);
                typeDirPath = this.getServletContext().getRealPath(typeDir);
            }

            File var26 = new File(typeDirPath);
            UtilsFile.checkDirAndCreate(var26);
            File currentDir = new File(var26, currentFolderStr);
            if(!currentDir.exists()) {
                ur = UploadResponse.UR_INVALID_CURRENT_FOLDER;
            } else {
                String newFilename = null;
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");

                try {
                    List e = upload.parseRequest(request);
                    FileItem var27 = (FileItem)e.get(0);
                    String rawName = UtilsFile.sanitizeFileName(var27.getName());
                    String filename = FilenameUtils.getName(rawName);
                    String baseName = FilenameUtils.removeExtension(filename);
                    String extension = FilenameUtils.getExtension(filename);
                    Integer[] pathToSave;
                    if(!ExtensionsHandler.isAllowed(var25, extension)) {
                        pathToSave = new Integer[]{Integer.valueOf(202)};
                        ur = new UploadResponse(pathToSave);
                    } else if(var27.getSize() > 3145728L) {
                        pathToSave = new Integer[]{Integer.valueOf(204)};
                        ur = new UploadResponse(pathToSave);
                    } else {
                        filename = UUID.randomUUID().toString() + "." + extension;
                        filename = makeFileName(currentDir.getPath(), filename);
                        File var28 = new File(currentDir, filename);

                        for(int counter = 1; var28.exists(); ++counter) {
                            newFilename = baseName.concat("(").concat(String.valueOf(counter)).concat(")").concat(".").concat(extension);
                            var28 = new File(currentDir, newFilename);
                        }

                        String[] params1;
                        if(Utils.isEmpty(newFilename)) {
                            params1 = new String[]{"0", UtilsResponse.constructResponseUrl(request, var25, currentFolderStr, true, ConnectorHandler.isFullUrl()).concat(filename)};
                            ur = new UploadResponse(params1);
                        } else {
                            params1 = new String[]{"201", UtilsResponse.constructResponseUrl(request, var25, currentFolderStr, true, ConnectorHandler.isFullUrl()).concat(newFilename), newFilename};
                            ur = new UploadResponse(params1);
                        }

                        if(var25.equals(ResourceTypeHandler.IMAGE) && ConnectorHandler.isSecureImageUploads()) {
                            if(UtilsFile.isImage(var27.getInputStream())) {
                                var27.write(var28);
                            } else {
                                Integer[] var29 = new Integer[]{Integer.valueOf(202)};
                                var27.delete();
                                ur = new UploadResponse(var29);
                            }
                        } else {
                            var27.write(var28);
                        }
                    }
                } catch (Exception var24) {
                    Integer[] params = new Integer[]{Integer.valueOf(203)};
                    ur = new UploadResponse(params);
                }
            }
        }

        out.print(ur);
        out.flush();
        out.close();
        logger.debug("Exiting Connector#doPost");
    }

    public static String makeFileName(String basePath, String filename) {
        String subPath = sdf.format(new Date());
        File dir = new File(basePath + "/" + subPath);
        if(!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("创建目录失败:" + dir.getPath());
        } else {
            String uuid = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(filename);
            return subPath + "/" + uuid + "." + extension;
        }
    }
}
