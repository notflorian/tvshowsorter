package org.notflorian;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTest {
    
    private File createFile(String name) throws IOException {
        File file = new File("target/generated-test-sources/" + name);
        file.createNewFile();
        file.deleteOnExit();

        return file;
    }

    @Test
    public void isVideoFile() throws IOException {
        assertTrue(Main.isVideoFile(createFile("Continuum.S04E01.Lost.Hours.WEB-DL.x264-FUM.mp4")));
        assertTrue(Main.isVideoFile(createFile("Burn.Notice.S04E07.Past.and.Future.Tense.FASTSUB.VOSTFR.HDTV.XviD-iDC.avi")));
        assertTrue(Main.isVideoFile(createFile("Orange.Is.The.New.Black.S01E02.720p.WEBRip.AAC2.0.H.264-Abjex.mkv")));
        assertTrue(Main.isVideoFile(createFile("The.IT.Crowd.S03E06.FINAL.VOSTFR.DVDRiP.XviD-DRAGONS.AVI")));
        assertFalse(Main.isVideoFile(createFile("Continuum.S04E01.Lost.Hours.WEB-DL.x264-FUM.srt")));
        assertFalse(Main.isVideoFile(createFile("Continuum.S04E01.Lost.Hours.WEB-DL.x264-FUM.nfo")));
    }
}