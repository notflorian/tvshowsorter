package org.notflorian;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowInfos {

    public static final String[] VERSIONS = new String[]{"FINAL", "REPACK", "REAL PROPER", "PROPER"};

    public static final String[] LANGUAGES = new String[]{"FASTSUB", "FRENCH SUBFORCED", "SUBFORCED", "VOSTFR", "TRUEFRENCH", "VFF", "VFQ", "VFI", "VO", "VF", "VOST"};

    public static final String[] VIDEO_CODECS = new String[]{"x264", "H.264", "H264", "XviD", "DviX"};

    public static final String[] AUDIO_CODECS = new String[]{"AAC2.0", "AAC", "DD5.1", "AC3"};

    public static final String[] DEFINITIONS = {"144p", "240p", "360p", "480p", "720p", "1080p", "nHD"};

    public static final String[] SOURCES = {"REAL HDTV", "HDTV", "WEBRip", "WEB-DL", "DVDRip", "BDRip", "DVDScr", "PPVRip", "R5", "TS", "CAM", "DVD-R"};

    public static final Pattern NUMBER_PATTERN = Pattern.compile("[Ss](\\d{2})[Ee](\\d{2})[Ee]?(\\d{2})?");


    private String fileName;
    private String name;
    private String year;
    private String season;
    private String episode;
    private String version; // PROPER or FINAL PROPER or REAL PROPER or REPACK
    private String title;
    private String language; // FASTSUB VOSTFR or VOSTFR
    private String definition; // 720p
    private String source; // HDTV or WEBRip or WEB-DL
    private String videoCodec; // x264 or XviD or H 264
    private String audioCodec; // AAC or AAC2.0 or DD5.1
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
        StringBuilder videoCodec = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, VIDEO_CODECS, videoCodec);
        if (videoCodec.length() > 0) {
            this.videoCodec = videoCodec.toString();
        }

        // Audio Codec
        StringBuilder audioCodec = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, AUDIO_CODECS, audioCodec);
        if (audioCodec.length() > 0) {
            this.audioCodec = audioCodec.toString();
        }

        tmpFileName = tmpFileName.replace('.', ' ');

        // Version
        StringBuilder version = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, VERSIONS, version);
        if (version.length() > 0) {
            this.version = version.toString();
        }

        // Language
        StringBuilder language = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, LANGUAGES, language);
        if (language.length() > 0) {
            this.language = language.toString();
        }

        // Source
        StringBuilder source = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, SOURCES, source);
        if (source.length() > 0) {
            this.source = source.toString();
        }

        // Definition
        StringBuilder definition = new StringBuilder();
        tmpFileName = findWholeWord(tmpFileName, DEFINITIONS, definition);
        if (definition.length() > 0) {
            this.definition = definition.toString();
        }

        // Team
        int teamIndex = tmpFileName.lastIndexOf("-");
        if (teamIndex != -1) {
            team = tmpFileName.substring(teamIndex + 1, tmpFileName.length());
            tmpFileName = tmpFileName.replace("-" + team, "");
        }

        StringBuilder name = new StringBuilder();
        StringBuilder year = new StringBuilder();
        StringBuilder season = new StringBuilder();
        StringBuilder episode = new StringBuilder();
        StringBuilder title = new StringBuilder();

        // Split file name on whitespaces
        String[] infos = tmpFileName.split("\\s");
        for (String info : infos) {
            if (StringUtils.isNotEmpty(info)) {

                if (season.length() == 0) {

                    Matcher numberMatcher = NUMBER_PATTERN.matcher(info);

                    if (!numberMatcher.find()) {

                        if (!info.matches("\\d{4}")) {
                            // Name
                            if (name.length() != 0) {
                                name.append(' ');
                            }

                            name.append(info);

                        } else {
                            // Year (ex: 2016)
                            year.append(info);
                        }

                    } else {
                        // Season & Episode (ex: S01E01E02)
                        season.append(numberMatcher.group(1));

                        for (int i = 2; i <= numberMatcher.groupCount(); i++) {
                            String group = numberMatcher.group(i);

                            if (group != null) {
                                if (episode.length() != 0) {
                                    episode.append(' ');
                                }

                                episode.append(group);
                            }
                        }
                    }

                } else {
                    // Title
                    if (title.length() != 0) {
                        title.append(' ');
                    }

                    title.append(info);

                }
            }
        }

        if (name.length() > 0) {
            this.name = name.toString();
        }

        if (year.length() > 0) {
            this.year = year.toString();
        }

        if (season.length() > 0) {
            this.season = season.toString();
        }

        if (episode.length() > 0) {
            this.episode = episode.toString();
        }

        if (title.length() > 0) {
            this.title = title.toString();
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

    private String findWholeWord(String fileName, String[] words, StringBuilder field) {
        String tmpFileName = fileName;

        for (String word : words) {
            if (StringUtils.containsIgnoreCase(tmpFileName, word)) {

                int index = StringUtils.indexOfIgnoreCase(tmpFileName, word);

                // Check char before the found word
                if (index == 0 || (index > 0 && isCharWordSeparator(tmpFileName.charAt(index - 1)))) {

                    // Check char after found word
                    if (index + word.length() == tmpFileName.length() || isCharWordSeparator(tmpFileName.charAt(index + word.length()))) {
                        if (field.length() > 0) {
                            field.append(' ');
                        }

                        field.append(word);

                        tmpFileName = tmpFileName.replace(tmpFileName.substring(index, index + word.length()), "");
                    }
                }
            }
        }

        return tmpFileName;
    }

    private boolean isCharWordSeparator(char c) {
        return c == ' '  ||  c == '.' || c == '-' || c == '_' || c == '[' || c == ']'  || c == '('  || c == ')';
    }


}
