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

import java.util.Iterator;
import java.util.List;

public class Main {

    public static final String SEASON = "Saison";

    public static final String[] VIDEO_EXTENSIONS = new String[]{".mp4", ".avi", ".mkv", ".wmv", ".mpg", ".mov"};
    public static final String PREFIX_ACTIVE = "_";


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

                            for (Iterator<File> it = FileUtils.iterateFiles(file, VIDEO_EXTENSIONS, false); it.hasNext(); ) {
                                File showFile = it.next();
                                File renamedShowFile = new File(file.getCanonicalPath() + showFile.getName().substring(showFile.getName().lastIndexOf('.')));
                                if (showFile.renameTo(renamedShowFile)) {
                                    System.out.println("Renamed file " + showFile.getName() + " to " + renamedShowFile.getName());

                                } else {
                                    throw new IOException("Failed to rename file " + showFile.getName());
                                }

                                processFile(showsRootDirectory, renamedShowFile);
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

    public static boolean isVideoFile(File file) {
        String name = file.getName().toLowerCase();

        return file.isFile() && Arrays.asList(VIDEO_EXTENSIONS).contains(name.substring(name.lastIndexOf('.')));
    }


    public static void processFile(File showsRootDirectory, File file) throws IOException {
        ShowInfos showInfos = new ShowInfos(file.getName());
        System.out.println("Processing show " + showInfos);

        File showDir = null;
        File[] showsDirs = showsRootDirectory.listFiles();
        if (showsDirs != null) {
            int i = 0;
            while (showDir == null && i < showsDirs.length) {
                String showsDirName = showsDirs[i].getName().replace(".", " ");
                if (showsDirName.equalsIgnoreCase(showInfos.getName()) || showsDirName.equalsIgnoreCase(PREFIX_ACTIVE + showInfos.getName())) {
                    showDir = showsDirs[i];
                }
            }
        }

        if (showDir == null) {
            // Create show directory
            showDir = new File(showsRootDirectory.getCanonicalPath() + "/" + PREFIX_ACTIVE + showInfos.getName() + "/");
            if (!showDir.mkdirs()) {
                throw new IOException("Failed to create directory " + showDir.getCanonicalPath());
            }
        }

        File seasonDir = new File(showDir.getCanonicalPath() + "/" + SEASON + " " + Integer.valueOf(showInfos.getSeason()));
        if (!seasonDir.exists()) {
            // Create season directory
            if (!seasonDir.mkdirs()) {
                throw new IOException("Failed to create directory " + file.getCanonicalPath());
            }
        }

        Files.move(file.toPath(), Paths.get(seasonDir.getCanonicalPath(), file.getName()), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("File " + file.getName() + " moved to " + seasonDir.getCanonicalPath());
        System.out.println();
    }
}
