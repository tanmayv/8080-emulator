import java.io.IOException;
import java.net.URISyntaxException;

import cpu.CPU;
import cpu.State8080;

/*
   Space Invaders, (C) Taito 1978, Midway 1979

   CPU: Intel 8080 @ 2MHz (CPU similar to the (newer) Zilog Z80)

   Interrupts: $cf (RST 8) at the start of vblank, $d7 (RST $10) at the end of vblank.

   Video: 256(x)*224(y) @ 60Hz, vertical monitor. Colours are simulated with a
   plastic transparent overlay and a background picture.
   Video hardware is very simple: 7168 bytes 1bpp bitmap (32 bytes per scanline).

   Sound: SN76477 and samples.

   Memory map:
    ROM
    $0000-$07ff:    invaders.h
    $0800-$0fff:    invaders.g
    $1000-$17ff:    invaders.f
    $1800-$1fff:    invaders.e

    RAM
    $2000-$23ff:    work RAM
    $2400-$3fff:    video RAM

    $4000-:     RAM mirror
 */

public class Main {
  public static void main(String[] args) throws IOException, URISyntaxException,
      InterruptedException {
    byte[] content = FileReader.getProgram("rom/game.rom");
    State8080 state = new State8080(content);
    int counter = 0;
    while (true) {
      CPU.emulate8080Op(state);
//      if(counter ++ > 1553)
//      Thread.sleep(1000);
    }
//    byte a = (byte) 0xab;
//    byte b = (byte) ((byte) 0xcd & 0xFF);
//
//    int c = a << 8 ;
//    c = c & 0xFFFF;
//    System.out.println(String.format("%04x", c));
//    c = c | (b & 0xFF);
//    System.out.println(String.format("%04x", c));
  }
}

