package org.notflorian;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final String SEASON = "Saison";

    public static final List<String> VIDEO_EXTENSIONS = Arrays.asList(".mp4", ".avi", ".mkv", ".wmv", ".mpg", ".mov");


    public static void main(String[] args) {
        try {
            // Check args
            if (args.length != 2 || StringUtils.isEmpty(args[0]) || StringUtils.isEmpty(args[1])) {
                throw new IOException("usage ./tvshowsorter <inputDir> <showsRootDir>");
            }

            File inputDirectory = new File(args[0]);
            if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
                throw new IOException("Wrong input directory");
            }

            File showsRootDirectory = new File(args[1]);
            if (!showsRootDirectory.exists() || !showsRootDirectory.isDirectory()) {
                throw new IOException("Wrong shows root directory");
            }

            // List files and directory in the input dir
            File[] inputFiles = inputDirectory.listFiles();
            if (inputFiles != null) {
                for (File file : inputFiles) {
                    if (file.isDirectory()) {
                        if (!"synoscheduler".equals(file.getName())) {

                            // If the show file is in a subdirectory, rename it to the parent directory name and move it
                            File[] showFiles = file.listFiles();
                            if (showFiles != null) {
                                for (File showFile : showFiles) {
                                    if (isVideoFile(showFile)) {
                                        File renamedShowFile = new File(file.getCanonicalPath() + showFile.getName().substring(showFile.getName().lastIndexOf('.')));
                                        if (showFile.renameTo(renamedShowFile)) {
                                            System.out.println("Renamed file " + showFile.getName() + " to " + renamedShowFile.getName());

                                        } else {
                                            throw new IOException("Failed to rename file " + showFile.getName());
                                        }

                                        processFile(showsRootDirectory, renamedShowFile);
                                    } else {
                                        showFile.deleteOnExit();
                                    }
                                }
                            }

                            FileUtils.deleteDirectory(file);
                        }

                    } else if (isVideoFile(file)) {
                        processFile(showsRootDirectory, file);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static boolean isVideoFile(File file)  {
        String name = file.getName().toLowerCase();

        return file.isFile() && VIDEO_EXTENSIONS.contains(name.substring(name.lastIndexOf('.')));
    }


    public static void processFile(File showsRootDirectory, File file) throws IOException {
        ShowInfos showInfos = new ShowInfos(file.getName());
        System.out.println("Processing show " + showInfos);

        File showDir = new File(showsRootDirectory.getCanonicalPath() + "/" + showInfos.getName() + "/");
        if (!showDir.exists()) {
            showDir = new File(showsRootDirectory.getCanonicalPath()+ "/" + "_" + showInfos.getName() + "/");

            if (!showDir.exists()) {
                if (!showDir.mkdirs()) {
                    throw new IOException("Failed to create directory " + showDir.getCanonicalPath());
                }
            }
        }

        File seasonDir = new File(showDir.getCanonicalPath() + "/" + SEASON + " " + Integer.valueOf(showInfos.getSeason()));
        if (!seasonDir.exists()) {
            if (!seasonDir.mkdirs()) {
                throw new IOException("Failed to create directory " + file.getCanonicalPath());
            }
        }

        Files.move(file.toPath(), Paths.get(seasonDir.getCanonicalPath(), file.getName()), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File " + file.getName() + " moved to " + seasonDir.getCanonicalPath());
        System.out.println();
    }
}
