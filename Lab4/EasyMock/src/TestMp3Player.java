/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import junit.framework.*;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;


public class TestMp3Player extends TestCase {

  protected Mp3Player mp3;
  protected Mp3Player mp3_control;
  protected ArrayList list = new ArrayList();

  public void setUp() {
      mp3_control = createMock(Mp3Player.class);
      mp3 = (Mp3Player)mp3_control;


      list = new ArrayList();
    list.add("Bill Chase -- Open Up Wide");
    list.add("Jethro Tull -- Locomotive Breath");
    list.add("The Boomtown Rats -- Monday");
    list.add("Carl Orff -- O Fortuna");
  }

  public void testPlay() {
    
    mp3.loadSongs(list);
    expect(mp3.isPlaying()).andReturn(false);
    mp3.play();
    expect(mp3.isPlaying()).andReturn(true);
    expect(mp3.currentPosition()).andReturn(2.0);
    mp3.pause();
    expect(mp3.currentPosition()).andReturn(2.0);
    mp3.stop();
    expect(mp3.currentPosition()).andReturn(0.0);

    replay(mp3_control);     


    assertFalse(mp3.isPlaying());
    assertTrue(mp3.isPlaying());
    assertTrue(mp3.currentPosition() != 0.0);
    assertTrue(mp3.currentPosition() != 0.0);
    assertEquals(mp3.currentPosition(), 0.0, 0.1);

  }

  public void testPlayNoList() {

    expect(mp3.isPlaying()).andReturn(false);
    mp3.play();
    expect(mp3.isPlaying()).andReturn(true);
    expect(mp3.currentPosition()).andReturn(0.0);
    mp3.pause();
    expect(mp3.currentPosition()).andReturn(0.0);
    expect(mp3.isPlaying()).andReturn(false);
    mp3.stop();
    expect(mp3.currentPosition()).andReturn(0.0);
    expect(mp3.isPlaying()).andReturn(false);

    replay(mp3_control);

    // Don't set the list up
    assertFalse(mp3.isPlaying());
    assertTrue(mp3.isPlaying());
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertFalse(mp3.isPlaying());
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertFalse(mp3.isPlaying());
  }

  public void testAdvance() {

    mp3.loadSongs(list);
    mp3.play();

    expect(mp3.isPlaying()).andReturn(true);
    mp3.prev();
    expect(mp3.currentSong()).andReturn("Bill Chase -- Open Up Wide");
    expect(mp3.isPlaying()).andReturn(true);

    mp3.next();
    expect(mp3.currentSong()).andReturn((String) list.get(1));
    mp3.next();
    expect(mp3.currentSong()).andReturn((String) list.get(2));
    mp3.prev();
    expect(mp3.currentSong()).andReturn((String) list.get(1));
    mp3.next();
    expect(mp3.currentSong()).andReturn((String) list.get(2));
    mp3.next();
    expect(mp3.currentSong()).andReturn((String) list.get(3));
    mp3.next();
    expect(mp3.currentSong()).andReturn((String) list.get(3));
    expect(mp3.isPlaying()).andReturn(true);

    replay(mp3_control);

    assertEquals(mp3.currentSong(), list.get(0));
    assertTrue(mp3.isPlaying());
    assertEquals(mp3.currentSong(), list.get(1));
    assertEquals(mp3.currentSong(), list.get(2));
    assertEquals(mp3.currentSong(), list.get(1));
    assertEquals(mp3.currentSong(), list.get(2));
    assertEquals(mp3.currentSong(), list.get(3));
    assertEquals(mp3.currentSong(), list.get(3));
    assertTrue(mp3.isPlaying());
  }

}