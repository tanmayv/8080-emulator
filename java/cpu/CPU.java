package cpu;

public class CPU {
  private static void unimplementedInstruction(State8080 state) {
    System.err.print(String.format("Err: Unimplemented Instrunction %04x: %02x", state.pc, state.memory[state.pc]));
    System.exit(1);
  }

  public static int emulate8080Op(State8080 state) {
    byte opcode = state.memory[state.pc];
    byte opcode1 = state.memory[state.pc + 1];
    byte opcode2 = state.memory[state.pc + 2];
    printMem(state.pc, state);

    switch (opcode & 0xFF) {
      case 0x00: break;                                 // NOP
      case 0x01:                                        // LXI B, D16
        state.c = opcode1;
        state.b = opcode2;
        state.pc += 2;
        break;
      case 0x02: unimplementedInstruction(state);
      case 0x03: unimplementedInstruction(state);
      case 0x04: unimplementedInstruction(state);
      case 0x05: unimplementedInstruction(state);
      case 0x06: unimplementedInstruction(state);
      case 0x07: unimplementedInstruction(state);
      case 0x08: unimplementedInstruction(state);
      case 0x09: unimplementedInstruction(state);
      case 0x0a: unimplementedInstruction(state);
      case 0x0b: unimplementedInstruction(state);
      case 0x0c: unimplementedInstruction(state);
      case 0x0d: unimplementedInstruction(state);
      case 0x0e: unimplementedInstruction(state);
      case 0x0f: unimplementedInstruction(state);
      case 0x10: unimplementedInstruction(state);
      case 0x11: unimplementedInstruction(state);
      case 0x12: unimplementedInstruction(state);
      case 0x13: unimplementedInstruction(state);
      case 0x14: unimplementedInstruction(state);
      case 0x15: unimplementedInstruction(state);
      case 0x16: unimplementedInstruction(state);
      case 0x17: unimplementedInstruction(state);
      case 0x18: unimplementedInstruction(state);
      case 0x19: unimplementedInstruction(state);
      case 0x1a: unimplementedInstruction(state);
      case 0x1b: unimplementedInstruction(state);
      case 0x1c: unimplementedInstruction(state);
      case 0x1d: unimplementedInstruction(state);
      case 0x1e: unimplementedInstruction(state);
      case 0x1f: unimplementedInstruction(state);
      case 0x20: unimplementedInstruction(state);
      case 0x21: unimplementedInstruction(state);
      case 0x22: unimplementedInstruction(state);
      case 0x23: unimplementedInstruction(state);
      case 0x24: unimplementedInstruction(state);
      case 0x25: unimplementedInstruction(state);
      case 0x26: unimplementedInstruction(state);
      case 0x27: unimplementedInstruction(state);
      case 0x28: unimplementedInstruction(state);
      case 0x29: unimplementedInstruction(state);
      case 0x2a: unimplementedInstruction(state);
      case 0x2b: unimplementedInstruction(state);
      case 0x2c: unimplementedInstruction(state);
      case 0x2d: unimplementedInstruction(state);
      case 0x2e: unimplementedInstruction(state);
      case 0x2f: unimplementedInstruction(state);
      case 0x30: unimplementedInstruction(state);
      case 0x31: unimplementedInstruction(state);
      case 0x32: unimplementedInstruction(state);
      case 0x33: unimplementedInstruction(state);
      case 0x34: unimplementedInstruction(state);
      case 0x35: unimplementedInstruction(state);
      case 0x36: unimplementedInstruction(state);
      case 0x37: unimplementedInstruction(state);
      case 0x38: unimplementedInstruction(state);
      case 0x39: unimplementedInstruction(state);
      case 0x3a: unimplementedInstruction(state);
      case 0x3b: unimplementedInstruction(state);
      case 0x3c: unimplementedInstruction(state);
      case 0x3d: unimplementedInstruction(state);
      case 0x3e: unimplementedInstruction(state);
      case 0x3f: unimplementedInstruction(state);
      case 0x40: unimplementedInstruction(state);
      case 0x41:                                        // MOV B,C
        state.b = state.c; break;
      case 0x42:                                        // MOV B,D
        state.b = state.d; break;
      case 0x43:                                        // MOV B,E
        state.b = state.e; break;
      case 0x44: unimplementedInstruction(state);
      case 0x45: unimplementedInstruction(state);
      case 0x46: unimplementedInstruction(state);
      case 0x47: unimplementedInstruction(state);
      case 0x48: unimplementedInstruction(state);
      case 0x49: unimplementedInstruction(state);
      case 0x4a: unimplementedInstruction(state);
      case 0x4b: unimplementedInstruction(state);
      case 0x4c: unimplementedInstruction(state);
      case 0x4d: unimplementedInstruction(state);
      case 0x4e: unimplementedInstruction(state);
      case 0x4f: unimplementedInstruction(state);
      case 0x50: unimplementedInstruction(state);
      case 0x51: unimplementedInstruction(state);
      case 0x52: unimplementedInstruction(state);
      case 0x53: unimplementedInstruction(state);
      case 0x54: unimplementedInstruction(state);
      case 0x55: unimplementedInstruction(state);
      case 0x56: unimplementedInstruction(state);
      case 0x57: unimplementedInstruction(state);
      case 0x58: unimplementedInstruction(state);
      case 0x59: unimplementedInstruction(state);
      case 0x5a: unimplementedInstruction(state);
      case 0x5b: unimplementedInstruction(state);
      case 0x5c: unimplementedInstruction(state);
      case 0x5d: unimplementedInstruction(state);
      case 0x5e: unimplementedInstruction(state);
      case 0x5f: unimplementedInstruction(state);
      case 0x60: unimplementedInstruction(state);
      case 0x61: unimplementedInstruction(state);
      case 0x62: unimplementedInstruction(state);
      case 0x63: unimplementedInstruction(state);
      case 0x64: unimplementedInstruction(state);
      case 0x65: unimplementedInstruction(state);
      case 0x66: unimplementedInstruction(state);
      case 0x67: unimplementedInstruction(state);
      case 0x68: unimplementedInstruction(state);
      case 0x69: unimplementedInstruction(state);
      case 0x6a: unimplementedInstruction(state);
      case 0x6b: unimplementedInstruction(state);
      case 0x6c: unimplementedInstruction(state);
      case 0x6d: unimplementedInstruction(state);
      case 0x6e: unimplementedInstruction(state);
      case 0x6f: unimplementedInstruction(state);
      case 0x70: unimplementedInstruction(state);
      case 0x71: unimplementedInstruction(state);
      case 0x72: unimplementedInstruction(state);
      case 0x73: unimplementedInstruction(state);
      case 0x74: unimplementedInstruction(state);
      case 0x75: unimplementedInstruction(state);
      case 0x76: unimplementedInstruction(state);
      case 0x77: unimplementedInstruction(state);
      case 0x78: unimplementedInstruction(state);
      case 0x79: unimplementedInstruction(state);
      case 0x7a: unimplementedInstruction(state);
      case 0x7b: unimplementedInstruction(state);
      case 0x7c: unimplementedInstruction(state);
      case 0x7d: unimplementedInstruction(state);
      case 0x7e: unimplementedInstruction(state);
      case 0x7f: unimplementedInstruction(state);
      case 0x80:                                         // ADD B
      {
        short answer = (short) ((short)state.a + (short)state.b);
        state.cc.z = (short) (((answer & 0xFF) == 0) ? 1: 0);
        state.cc.s = (short) (((answer & 0x80) != 0) ? 1: 0);
        state.cc.cy = (short) ((answer > 0xff) ? 1: 0);
        state.cc.p = getParity(answer & 0xFF);
        state.a = (byte) (answer & 0xFF);
        break;
      }
      case 0x81: unimplementedInstruction(state);
      case 0x82: unimplementedInstruction(state);
      case 0x83: unimplementedInstruction(state);
      case 0x84: unimplementedInstruction(state);
      case 0x85: unimplementedInstruction(state);
      case 0x86:                                          // ADD M
      {
        short offset = (short) (state.h << 8 | state.l);
        short answer = (short) ((short)state.a + (short)state.memory[offset]);
        state.cc.z = (short) (((answer & 0xFF) == 0) ? 1: 0);
        state.cc.s = (short) (((answer & 0x80) != 0) ? 1: 0);
        state.cc.cy = (short) ((answer > 0xff) ? 1: 0);
        state.cc.p = getParity(answer & 0xFF);
        state.a = (byte) (answer & 0xFF);
        break;
      }
      case 0x87: unimplementedInstruction(state);
      case 0x88: unimplementedInstruction(state);
      case 0x89: unimplementedInstruction(state);
      case 0x8a: unimplementedInstruction(state);
      case 0x8b: unimplementedInstruction(state);
      case 0x8c: unimplementedInstruction(state);
      case 0x8d: unimplementedInstruction(state);
      case 0x8e: unimplementedInstruction(state);
      case 0x8f: unimplementedInstruction(state);
      case 0x90: unimplementedInstruction(state);
      case 0x91: unimplementedInstruction(state);
      case 0x92: unimplementedInstruction(state);
      case 0x93: unimplementedInstruction(state);
      case 0x94: unimplementedInstruction(state);
      case 0x95: unimplementedInstruction(state);
      case 0x96: unimplementedInstruction(state);
      case 0x97: unimplementedInstruction(state);
      case 0x98: unimplementedInstruction(state);
      case 0x99: unimplementedInstruction(state);
      case 0x9a: unimplementedInstruction(state);
      case 0x9b: unimplementedInstruction(state);
      case 0x9c: unimplementedInstruction(state);
      case 0x9d: unimplementedInstruction(state);
      case 0x9e: unimplementedInstruction(state);
      case 0x9f: unimplementedInstruction(state);
      case 0xa0: unimplementedInstruction(state);
      case 0xa1: unimplementedInstruction(state);
      case 0xa2: unimplementedInstruction(state);
      case 0xa3: unimplementedInstruction(state);
      case 0xa4: unimplementedInstruction(state);
      case 0xa5: unimplementedInstruction(state);
      case 0xa6: unimplementedInstruction(state);
      case 0xa7: unimplementedInstruction(state);
      case 0xa8: unimplementedInstruction(state);
      case 0xa9: unimplementedInstruction(state);
      case 0xaa: unimplementedInstruction(state);
      case 0xab: unimplementedInstruction(state);
      case 0xac: unimplementedInstruction(state);
      case 0xad: unimplementedInstruction(state);
      case 0xae: unimplementedInstruction(state);
      case 0xaf: unimplementedInstruction(state);
      case 0xb0: unimplementedInstruction(state);
      case 0xb1: unimplementedInstruction(state);
      case 0xb2: unimplementedInstruction(state);
      case 0xb3: unimplementedInstruction(state);
      case 0xb4: unimplementedInstruction(state);
      case 0xb5: unimplementedInstruction(state);
      case 0xb6: unimplementedInstruction(state);
      case 0xb7: unimplementedInstruction(state);
      case 0xb8: unimplementedInstruction(state);
      case 0xb9: unimplementedInstruction(state);
      case 0xba: unimplementedInstruction(state);
      case 0xbb: unimplementedInstruction(state);
      case 0xbc: unimplementedInstruction(state);
      case 0xbd: unimplementedInstruction(state);
      case 0xbe: unimplementedInstruction(state);
      case 0xbf: unimplementedInstruction(state);
      case 0xc0: unimplementedInstruction(state);
      case 0xc1: unimplementedInstruction(state);
      case 0xc2:                                          // JNZ addr
        if (state.cc.z == 0)
          state.pc = opcode2 << 8 | opcode1;
        else
          state.pc += 2;
        return 0;
      case 0xc3:                                          // JMP addr
        state.pc = appendAddr(opcode2, opcode1);
        return 0;
      case 0xc4: unimplementedInstruction(state);
      case 0xc5: unimplementedInstruction(state);
      case 0xc6:                                          // ADI D8
      {
        short answer = (short) ((short)state.a + (short)opcode1);
        state.cc.z = (short) (((answer & 0xFF) == 0) ? 1: 0);
        state.cc.s = (short) (((answer & 0x80) != 0) ? 1: 0);
        state.cc.cy = (short) ((answer > 0xff) ? 1: 0);
        state.cc.p = getParity(answer & 0xFF);
        state.a = (byte) (answer & 0xFF);
        //might need to increment pc
        break;
      }
      case 0xc7: unimplementedInstruction(state);
      case 0xc8: unimplementedInstruction(state);
      case 0xc9: unimplementedInstruction(state);
      case 0xca: unimplementedInstruction(state);
      case 0xcb: unimplementedInstruction(state);
      case 0xcc: unimplementedInstruction(state);
      case 0xcd: unimplementedInstruction(state);
      case 0xce: unimplementedInstruction(state);
      case 0xcf: unimplementedInstruction(state);
      case 0xd0: unimplementedInstruction(state);
      case 0xd1: unimplementedInstruction(state);
      case 0xd2: unimplementedInstruction(state);
      case 0xd3: unimplementedInstruction(state);
      case 0xd4: unimplementedInstruction(state);
      case 0xd5: unimplementedInstruction(state);
      case 0xd6: unimplementedInstruction(state);
      case 0xd7: unimplementedInstruction(state);
      case 0xd8: unimplementedInstruction(state);
      case 0xd9: unimplementedInstruction(state);
      case 0xda: unimplementedInstruction(state);
      case 0xdb: unimplementedInstruction(state);
      case 0xdc: unimplementedInstruction(state);
      case 0xdd: unimplementedInstruction(state);
      case 0xde: unimplementedInstruction(state);
      case 0xdf: unimplementedInstruction(state);
      case 0xe0: unimplementedInstruction(state);
      case 0xe1: unimplementedInstruction(state);
      case 0xe2: unimplementedInstruction(state);
      case 0xe3: unimplementedInstruction(state);
      case 0xe4: unimplementedInstruction(state);
      case 0xe5: unimplementedInstruction(state);
      case 0xe6: unimplementedInstruction(state);
      case 0xe7: unimplementedInstruction(state);
      case 0xe8: unimplementedInstruction(state);
      case 0xe9: unimplementedInstruction(state);
      case 0xea: unimplementedInstruction(state);
      case 0xeb: unimplementedInstruction(state);
      case 0xec: unimplementedInstruction(state);
      case 0xed: unimplementedInstruction(state);
      case 0xee: unimplementedInstruction(state);
      case 0xef: unimplementedInstruction(state);
      case 0xf0: unimplementedInstruction(state);
      case 0xf1: unimplementedInstruction(state);
      case 0xf2: unimplementedInstruction(state);
      case 0xf3: unimplementedInstruction(state);
      case 0xf4: unimplementedInstruction(state);
      case 0xf5: unimplementedInstruction(state);
      case 0xf6: unimplementedInstruction(state);
      case 0xf7: unimplementedInstruction(state);
      case 0xf8: unimplementedInstruction(state);
      case 0xf9: unimplementedInstruction(state);
      case 0xfa: unimplementedInstruction(state);
      case 0xfb: unimplementedInstruction(state);
      case 0xfc: unimplementedInstruction(state);
      case 0xfd: unimplementedInstruction(state);
      case 0xfe: unimplementedInstruction(state);
      case 0xff: unimplementedInstruction(state);
    }
    state.pc += 1;

    return 0;
  }

  private static byte getParity(int n)
  {
    boolean parity = false;
    while(n != 0)
    {
      parity = !parity;
      n = n & (n-1);
    }
    return (byte) (parity ? 1 : 0);
  }

  private static short appendAddr(byte msb, byte lsb) {
    return (short) ((msb << 8) & 0xFFFF | (lsb & 0xFF));
  }

  public static void printMem(int index, State8080 state) {
    byte opcode = state.memory[index];
    byte opcode1 = state.memory[index + 1];
    byte opcode2 = state.memory[index + 2];
    System.out.println(String.format("%04x : %02x %02x %02x",index, opcode, opcode1, opcode2));
  }
}
