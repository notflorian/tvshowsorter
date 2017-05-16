package org.notflorian;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowInfos {

    private static final String[] VERSIONS = new String[]{"FINAL", "REPACK", "REAL PROPER", "PROPER"};

    private static final String[] LANGUAGES = new String[]{"FASTSUB", "FRENCH SUBFORCED", "SUBFORCED", "VOSTFR", "TRUEFRENCH", "VFF", "VFQ", "VFI", "VO", "VF", "VOST"};

    private static final String[] VIDEO_CODECS = new String[]{"x264", "H.264", "H264", "XviD", "DviX"};

    private static final String[] AUDIO_CODECS = new String[]{"AAC2.0", "AAC", "DD5.1", "AC3"};

    private static final String[] DEFINITIONS = {"144p", "240p", "360p", "480p", "720p", "1080p", "nHD"};

    private static final String[] SOURCES = {"REAL HDTV", "HDTV", "WEBRip", "WEB-DL", "DVDRip", "BDRip", "BRRip", "DVDScr", "PPVRip", "R5", "TS", "CAM", "DVD-R"};

    private static final Pattern NUMBER_PATTERN = Pattern.compile("[Ss](\\d+)[Ee](\\d+)[Ee]?(\\d+)?");


    private String fileName;
    private String name;
    private String year;
    private String season;
    private String episode;
    private String version;
    private String title;
    private String language;
    private String definition;
    private String source;
    private String videoCodec;
    private String audioCodec;
    private String team;
    private String site;
    private String fileType;

    public ShowInfos(String fileName) {
        this.fileName = fileName;

        String tmpFileName = fileName;

        // File Type
        fileType = tmpFileName.substring(tmpFileName.lastIndexOf('.') + 1, tmpFileName.length());
        tmpFileName = tmpFileName.substring(0, tmpFileName.lastIndexOf('.'));


        // Site
        int siteBeginIndex = tmpFileName.lastIndexOf('[');
        int siteEndIndex = tmpFileName.lastIndexOf(']');
        if (siteBeginIndex != -1 && siteEndIndex != -1) {
            site = tmpFileName.substring(siteBeginIndex + 1, siteEndIndex);
            tmpFileName = tmpFileName.replace("[" + site + "]", "");
        }

        // Video Codec
        StringBuilder videoCodecBuilder = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, VIDEO_CODECS, videoCodecBuilder);
        if (videoCodecBuilder.length() > 0) {
            videoCodec = videoCodecBuilder.toString();
        }

        // Audio Codec
        StringBuilder audioCodecbuilder = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, AUDIO_CODECS, audioCodecbuilder);
        if (audioCodecbuilder.length() > 0) {
            audioCodec = audioCodecbuilder.toString();
        }

        tmpFileName = tmpFileName.replace('.', ' ');

        // Version
        StringBuilder versionBuilder = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, VERSIONS, versionBuilder);
        if (versionBuilder.length() > 0) {
            version = versionBuilder.toString();
        }

        // Language
        StringBuilder languageBuilder = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, LANGUAGES, languageBuilder);
        if (languageBuilder.length() > 0) {
            language = languageBuilder.toString();
        }

        // Source
        StringBuilder sourceBuilder = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, SOURCES, sourceBuilder);
        if (sourceBuilder.length() > 0) {
            source = sourceBuilder.toString();
        }

        // Definition
        StringBuilder definitionBuilder = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, DEFINITIONS, definitionBuilder);
        if (definitionBuilder.length() > 0) {
            definition = definitionBuilder.toString();
        }

        // Team
        int teamIndex = tmpFileName.lastIndexOf('-');
        if (teamIndex != -1) {
            team = tmpFileName.substring(teamIndex + 1, tmpFileName.length());
            tmpFileName = tmpFileName.replace("-" + team, "");
        }

        StringBuilder nameBuilder = new StringBuilder();
        StringBuilder yearBuilder = new StringBuilder();
        StringBuilder seasonBuilder = new StringBuilder();
        StringBuilder episodeBuilder = new StringBuilder();
        StringBuilder titleBuilder = new StringBuilder();

        // Split file name on whitespaces
        String[] infos = tmpFileName.split("\\s");
        for (String info : infos) {
            if (StringUtils.isNotEmpty(info)) {

                if (seasonBuilder.length() == 0) {

                    Matcher numberMatcher = NUMBER_PATTERN.matcher(info);

                    if (!numberMatcher.find()) {

                        if (!info.matches("\\d{4}")) {
                            // Name
                            if (nameBuilder.length() != 0) {
                                nameBuilder.append(' ');
                            }

                            nameBuilder.append(info);

                        } else {
                            // Year (ex: 2016)
                            yearBuilder.append(info);
                        }

                    } else {
                        // Season & Episode (ex: S01E01E02)
                        seasonBuilder.append(numberMatcher.group(1));

                        for (int i = 2; i <= numberMatcher.groupCount(); i++) {
                            String group = numberMatcher.group(i);

                            if (group != null) {
                                if (episodeBuilder.length() != 0) {
                                    episodeBuilder.append(' ');
                                }

                                episodeBuilder.append(group);
                            }
                        }
                    }

                } else {
                    // Title
                    if (titleBuilder.length() != 0) {
                        titleBuilder.append(' ');
                    }

                    titleBuilder.append(info);

                }
            }
        }

        if (nameBuilder.length() > 0) {
            this.name = nameBuilder.toString();
        }

        if (yearBuilder.length() > 0) {
            this.year = yearBuilder.toString();
        }

        if (seasonBuilder.length() > 0) {
            this.season = seasonBuilder.toString();
        }

        if (episodeBuilder.length() > 0) {
            this.episode = episodeBuilder.toString();
        }

        if (titleBuilder.length() > 0) {
            this.title = titleBuilder.toString();
        }

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    @Override
    public String toString() {
        return "ShowInfos{" +
                "fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", season='" + season + '\'' +
                ", episode='" + episode + '\'' +
                ", version='" + version + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", definition='" + definition + '\'' +
                ", source='" + source + '\'' +
                ", videoCodec='" + videoCodec + '\'' +
                ", audioCodec='" + audioCodec + '\'' +
                ", team='" + team + '\'' +
                ", site='" + site + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    private static String findWholeWord(String fileName, String[] words, StringBuilder field) {
        String tmpFileName = fileName;

        for (String word : words) {
            if (StringUtils.containsIgnoreCase(tmpFileName, word)) {

                int index = StringUtils.indexOfIgnoreCase(tmpFileName, word);

                // Check char before and after the found word
                if ((index == 0 || (index > 0 && isCharWordSeparator(tmpFileName.charAt(index - 1)))) &&
                        (index + word.length() == tmpFileName.length() || isCharWordSeparator(tmpFileName.charAt(index + word.length())))) {

                    if (field.length() > 0) {
                        field.append(' ');
                    }

                    field.append(word);

                    tmpFileName = tmpFileName.replace(tmpFileName.substring(index, index + word.length()), "");
                }
            }
        }

        return tmpFileName;
    }

    private static boolean isCharWordSeparator(char c) {
        return c == ' '  ||  c == '.' || c == '-' || c == '_' || c == '[' || c == ']'  || c == '('  || c == ')';
    }


}
