package org.notflorian;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShowInfosTest {

    @Test
    public void test_11_22_63() {
        String fileName = "11.22.63.S01E04.HDTV.x264-LOL[rarbg].mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("11 22 63", infos.getName());
        assertNull(infos.getYear());
        assertEquals("01", infos.getSeason());
        assertEquals("04", infos.getEpisode());
        assertNull(infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertEquals("LOL", infos.getTeam());
        assertEquals("rarbg", infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_House_of_Cards() {
        String fileName = "House.of.Cards.2013.S02E01.WEBRip.x264-2HD.mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("House of Cards", infos.getName());
        assertEquals("2013", infos.getYear());
        assertEquals("02", infos.getSeason());
        assertEquals("01", infos.getEpisode());
        assertNull(infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEBRip", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertEquals("2HD", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_Breaking_Bad() {
        String fileName = "Breaking.Bad.S02E10.PROPER.VOSTFR.HDTV.XviD-DRAGONS.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Breaking Bad", infos.getName());
        assertNull(infos.getYear());
        assertEquals("02", infos.getSeason());
        assertEquals("10", infos.getEpisode());
        assertEquals("PROPER", infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertEquals("VOSTFR", infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertNull(infos.getAudioCodec());
        assertEquals("DRAGONS", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_LEGO_Star_Wars() {
        String fileName = "LEGO.Star.Wars.Droid.Tales.S01E05.Gambit.on.Geonosis.720p.WEB-DL.x264.AAC.mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("LEGO Star Wars Droid Tales", infos.getName());
        assertNull(infos.getYear());
        assertEquals("01", infos.getSeason());
        assertEquals("05", infos.getEpisode());
        assertNull(infos.getVersion());
        assertEquals("Gambit on Geonosis", infos.getTitle());
        assertEquals("720p", infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEB-DL", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertEquals("AAC", infos.getAudioCodec());
        assertNull(infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_Clone_Wars() {
        String fileName = "star.wars.the.clone.wars.s06e08.720p.webrip.x264-2hd.mkv";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("star wars the clone wars", infos.getName());
        assertNull(infos.getYear());
        assertEquals("06", infos.getSeason());
        assertEquals("08", infos.getEpisode());
        assertNull(infos.getVersion());
        assertNull(infos.getTitle());
        assertEquals("720p", infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEBRip", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertNull(infos.getAudioCodec());
        assertEquals("2hd", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mkv", infos.getFileType());
    }

    @Test
    public void test_HIMYM() {
        String fileName = "How.I.Met.Your.Mother.S09E06.REPACK.HDTV.x264-ASAP.mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("How I Met Your Mother", infos.getName());
        assertNull(infos.getYear());
        assertEquals("09", infos.getSeason());
        assertEquals("06", infos.getEpisode());
        assertEquals("REPACK", infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertNull(infos.getAudioCodec());
        assertEquals("ASAP", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_Community() {
        String fileName = "Community.S03E18.HDTV.x264.REPACK-LOL.mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Community", infos.getName());
        assertNull(infos.getYear());
        assertEquals("03", infos.getSeason());
        assertEquals("18", infos.getEpisode());
        assertEquals("REPACK", infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertNull(infos.getAudioCodec());
        assertEquals("LOL", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_Jessica_Jones() {
        String fileName = "Jessica.Jones.S01E02.AKA.Crush.Syndrome.nHD.WEBrip.x264-[MULVAcoded].mkv";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Jessica Jones", infos.getName());
        assertNull(infos.getYear());
        assertEquals("01", infos.getSeason());
        assertEquals("02", infos.getEpisode());
        assertNull(infos.getVersion());
        assertEquals("AKA Crush Syndrome", infos.getTitle());
        assertEquals("nHD", infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEBRip", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertNull(infos.getAudioCodec());
        assertEquals("", infos.getTeam());
        assertEquals("MULVAcoded", infos.getSite());
        assertEquals("mkv", infos.getFileType());
    }

    @Test
    public void test_OITNB() {
        String fileName = "Orange.Is.The.New.Black.S01E02.720p.WEBRip.AAC2.0.H.264-Abjex.mkv";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Orange Is The New Black", infos.getName());
        assertNull(infos.getYear());
        assertEquals("01", infos.getSeason());
        assertEquals("02", infos.getEpisode());
        assertNull(infos.getVersion());
        assertNull(infos.getTitle());
        assertEquals("720p", infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEBRip", infos.getSource());
        assertEquals("H.264", infos.getVideoCodec());
        assertEquals("AAC2.0", infos.getAudioCodec());
        assertEquals("Abjex", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mkv", infos.getFileType());
    }

    @Test
    public void test_Fringe() {
        String fileName = "Fringe.S02E23.FINAL.PROPER.VOSTFR.HDTV.XviD-ATeam.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Fringe", infos.getName());
        assertNull(infos.getYear());
        assertEquals("02", infos.getSeason());
        assertEquals("23", infos.getEpisode());
        assertEquals("FINAL PROPER", infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertEquals("VOSTFR", infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertEquals("ATeam", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_Haven() {
        String fileName = "Haven.S01E11.The.Trial.of.Audrey.Parker.PROPER.HDTV.XviD-FQM.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Haven", infos.getName());
        assertNull(infos.getYear());
        assertEquals("01", infos.getSeason());
        assertEquals("11", infos.getEpisode());
        assertEquals("PROPER", infos.getVersion());
        assertEquals("The Trial of Audrey Parker", infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertEquals("FQM", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_Man_vs_Wild() {
        String fileName = "Man.vs.Wild.S05E09.Cape.Wrath.Scotland.REAL.HDTV.XviD-MOMENTUM.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Man vs Wild", infos.getName());
        assertNull(infos.getYear());
        assertEquals("05", infos.getSeason());
        assertEquals("09", infos.getEpisode());
        assertNull(infos.getVersion());
        assertEquals("Cape Wrath Scotland", infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("REAL HDTV", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertEquals("MOMENTUM", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_Heroes() {
        String fileName = "Heroes.S04E01E02.REAL.PROPER.HDTV.XviD-GKS.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Heroes", infos.getName());
        assertNull(infos.getYear());
        assertEquals("04", infos.getSeason());
        assertEquals("01 02", infos.getEpisode());
        assertEquals("REAL PROPER", infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertEquals("GKS", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_Burn_Notice() {
        String fileName = "Burn.Notice.S04E07.Past.and.Future.Tense.FASTSUB.VOSTFR.HDTV.XviD-iDC.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Burn Notice", infos.getName());
        assertNull(infos.getYear());
        assertEquals("04", infos.getSeason());
        assertEquals("07", infos.getEpisode());
        assertNull(infos.getVersion());
        assertEquals("Past and Future Tense", infos.getTitle());
        assertNull(infos.getDefinition());
        assertEquals("FASTSUB VOSTFR", infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertEquals("iDC", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_IT_Crowd() {
        String fileName = "The.IT.Crowd.S03E06.FINAL.VOSTFR.DVDRiP.XviD-DRAGONS.avi";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("The IT Crowd", infos.getName());
        assertNull(infos.getYear());
        assertEquals("03", infos.getSeason());
        assertEquals("06", infos.getEpisode());
        assertEquals("FINAL", infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertEquals("VOSTFR", infos.getLanguage());
        assertEquals("DVDRip", infos.getSource());
        assertEquals("XviD", infos.getVideoCodec());
        assertEquals("DRAGONS", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("avi", infos.getFileType());
    }

    @Test
    public void test_Continuum() {
        String fileName = "Continuum.S04E01.Lost.Hours.WEB-DL.x264-FUM.mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Continuum", infos.getName());
        assertNull(infos.getYear());
        assertEquals("04", infos.getSeason());
        assertEquals("01", infos.getEpisode());
        assertNull( infos.getVersion());
        assertEquals("Lost Hours", infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEB-DL", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertEquals("FUM", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_AgentsOfSHIELD() {
        String fileName = "Marvels.Agents.of.S.H.I.E.L.D.S03E12.HDTV.x264-KILLERS[rarbg].mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("Marvels Agents of S H I E L D", infos.getName());
        assertNull(infos.getYear());
        assertEquals("03", infos.getSeason());
        assertEquals("12", infos.getEpisode());
        assertNull( infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("HDTV", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertEquals("KILLERS", infos.getTeam());
        assertEquals("rarbg", infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }

    @Test
    public void test_Daredevil() {
        String fileName = "marvels.daredevil.s02e01.webrip.x264-fum.mp4";

        ShowInfos infos = new ShowInfos(fileName);

        assertEquals(fileName, infos.getFileName());
        assertEquals("marvels daredevil", infos.getName());
        assertNull(infos.getYear());
        assertEquals("02", infos.getSeason());
        assertEquals("01", infos.getEpisode());
        assertNull( infos.getVersion());
        assertNull(infos.getTitle());
        assertNull(infos.getDefinition());
        assertNull(infos.getLanguage());
        assertEquals("WEBRip", infos.getSource());
        assertEquals("x264", infos.getVideoCodec());
        assertEquals("fum", infos.getTeam());
        assertNull(infos.getSite());
        assertEquals("mp4", infos.getFileType());
    }
}
