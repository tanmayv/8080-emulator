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
  static char shift0, shift1, shiftOffset;
  static long lastInterrupt;

  public static void main(String[] args) throws IOException, URISyntaxException,
      InterruptedException {
    byte[] content = FileReader.getProgram("rom/game.rom");
    State8080 state = new State8080(content);
    int counter = 0;
    while (true) {
      byte opcode = state.memory[state.pc];
      switch (opcode & 0xFF) {
        case 0xDB:{
          byte port = state.memory[state.pc + 1];
          state.a = machineIn(state, port);
          state.pc++;
          break;
        }
        case 0xD3: {
          byte port = state.memory[state.pc + 1];
          machineOut(port, state.a);
          state.pc++;
          break;
        }
        default:
          CPU.emulate8080Op(state);
      }
      long currentTime = System.currentTimeMillis();
      if( currentTime - lastInterrupt > (1/60) * 1000) {
        if(state.int_enable == 1) {
          CPU.generateInterrupt(state, 2);
          lastInterrupt = currentTime;
        }
      }
//      if(counter ++ > 1553)
//      Thread.sleep(1000);
    }
  }

  public static byte machineIn(State8080 state, byte port) {
    byte a = 0x00;
    switch (port & 0xFF) {
      case 3:
      {
        char v = (char) ((shift1 << 8) | shift0);
        a = (byte) ((v >> (8-shiftOffset)) & 0xFF);
      }
      break;
    }
    return a;
  }

  public static void machineOut(byte port, byte value) {
    switch (port & 0xFF) {
      case 2:
        shiftOffset = (char) (value & 0x7);
        break;
      case 4:
        shift0 = shift1;
        shift1 = (char) value;
        break;
    }
  }

}

