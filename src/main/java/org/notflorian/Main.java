package org.notflorian;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class Main {

    public static final String SEASON = "Saison";
    public static final String PREFIX_ACTIVE = "_";

    private static final String[] VIDEO_FILE_TYPE = new String[]{"mp4", "avi", "mkv", "wmv", "mpg", "mov"};
    private static final String[] SUBTITLES_FILE_TYPE = new String[]{"srt"};

    private static final List<String> IGNORED_VIDEO_FILES = Arrays.asList("rarbg.com.mp4", "rarbg.mp4");

    private static final Logger logger = LogManager.getLogger(Main.class);

    private Main() {
        // Private constructor to hide the implicit public one
    }

    public static void main(String... args) {
        try {
            // Check args
            if (args.length != 2 || StringUtils.isEmpty(args[0]) || StringUtils.isEmpty(args[1])) {
                throw new IOException("usage ./tvshowsorter <inputDir> <showsRootDir>");
            }

            File inputDirectory = new File(args[0]);
            if (!inputDirectory.exists() || !inputDirectory.isDirectory()) {
                throw new IOException("Wrong input directory");
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Input directory :" + inputDirectory.getCanonicalPath());
            }

            File showsRootDirectory = new File(args[1]);
            if (!showsRootDirectory.exists() || !showsRootDirectory.isDirectory()) {
                throw new IOException("Wrong shows root directory");
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Shows root directory" + showsRootDirectory.getCanonicalPath());
            }

            boolean error = false;

            // List files and directory in the input dir
            File[] inputFiles = inputDirectory.listFiles();
            if (inputFiles != null) {
                for (File file : inputFiles) {
                    if (file.isDirectory()) {
                        if (!"synoscheduler".equals(file.getName())) {
                            error |= processDirectory(showsRootDirectory, file);
                        }

                    } else if (isVideoFile(file)) {
                        try {
                            processFile(showsRootDirectory, file);

                        } catch (IOException e) {
                            logger.error("Error while processing file " + file.getCanonicalPath(), e);

                            error = true;
                        }

                    }
                }
            }

            if (error) {
                System.exit(-1);
            }
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            System.exit(-1);
        }
    }

    private static boolean processDirectory(File showsRootDirectory, File file) throws IOException {
        boolean error = false;

        // If the show file is in a subdirectory, rename it to the parent directory name and move it
        int nbVideoFiles = 0;
        for (Iterator<File> it = FileUtils.iterateFiles(file, VIDEO_FILE_TYPE, false); it.hasNext(); ) {
            File showFile = it.next();

            if (!IGNORED_VIDEO_FILES.contains(showFile.getName().toLowerCase(Locale.getDefault()))) {
                nbVideoFiles++;
            }

        }

        int processedFiles = 0;

        for (Iterator<File> it = FileUtils.iterateFiles(file, ArrayUtils.addAll(VIDEO_FILE_TYPE, SUBTITLES_FILE_TYPE), false); it.hasNext(); ) {
            try {
                File showFile = it.next();

                String originalName = showFile.getName();

                if (!IGNORED_VIDEO_FILES.contains(originalName.toLowerCase(Locale.getDefault()))) {
                    if (nbVideoFiles == 1) {
                        File renamedShowFile = new File(file, file.getName() + originalName.substring(originalName.lastIndexOf('.')));

                        if (showFile.renameTo(renamedShowFile)) {
                            logger.info("Renamed file " + showFile.getCanonicalPath() + " to " + renamedShowFile.getCanonicalPath());
                            showFile = renamedShowFile;

                        } else {
                            throw new IOException("Failed to rename file " + showFile.getName());
                        }
                    }

                    processFile(showsRootDirectory, showFile);
                    processedFiles++;
                }

            } catch (IOException e) {
                logger.error("Error while processing file " + file.getCanonicalPath(), e);

                error = true;
            }
        }


        if (!error && nbVideoFiles > 0 && processedFiles == nbVideoFiles) {
            logger.info("Delete directory " + file.getCanonicalPath());
            FileUtils.deleteDirectory(file);
        }

        return error;
    }

    public static boolean isVideoFile(File file) {
        String name = file.getName().toLowerCase(Locale.getDefault());

        return file.isFile() && Arrays.asList(VIDEO_FILE_TYPE).contains(name.substring(name.lastIndexOf('.') + 1));
    }


    public static void processFile(File showsRootDirectory, File file) throws IOException {
        ShowInfos showInfos = new ShowInfos(file.getName());
        logger.info("Processing show " + showInfos);
        if (showInfos.getName() == null || showInfos.getSeason() == null) {
            throw new IOException("Invalid show infos " + showInfos);
        }

        File showDir = null;

        File[] showsDirs = showsRootDirectory.listFiles();
        if (showsDirs != null) {
            int i = 0;
            while (showDir == null && i < showsDirs.length) {
                String showsDirName = showsDirs[i].getName().replace(".", " ");
                if (showsDirName.equalsIgnoreCase(showInfos.getName()) || showsDirName.equalsIgnoreCase(PREFIX_ACTIVE + showInfos.getName())) {
                    showDir = showsDirs[i];
                }

                i++;
            }
        }

        if (showDir == null) {
            // Create show directory
            showDir = new File(showsRootDirectory.getCanonicalPath() + "/" + PREFIX_ACTIVE + showInfos.getName() + "/");
            if (showDir.mkdirs()) {
                logger.info("Directory " + showDir.getCanonicalPath() + " created");

            } else {
                throw new IOException("Failed to create directory " + showDir.getCanonicalPath());
            }
        }

        File seasonDir = new File(showDir.getCanonicalPath() + "/" + SEASON + " " + Integer.valueOf(showInfos.getSeason()));
        String seasonDirPath = seasonDir.getCanonicalPath();
        // Create season directory
        if (!seasonDir.exists()) {
            if (seasonDir.mkdirs()) {
                logger.info("Directory " + seasonDirPath + " created");

            } else {
                throw new IOException("Failed to create directory " + seasonDirPath);
            }
        }

        Files.move(file.toPath(), Paths.get(seasonDirPath, file.getName()), StandardCopyOption.REPLACE_EXISTING);

        logger.info("File " + file.getCanonicalPath() + " moved to " + seasonDirPath);
    }
}
