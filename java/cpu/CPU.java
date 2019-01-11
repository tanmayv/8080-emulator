package cpu;

public class CPU {
  static long instructions = 1;
  private static void unimplementedInstruction(State8080 state) {
    System.err.print(String.format("Err: Unimplemented Instrunction %04x: %02x", (short) state.pc, state.memory[state.pc]));
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
      case 0x05:                                        // DCR B
      {
        short answer = (short) (state.b - 1);
        state.cc.z = checkZ(answer);
        state.cc.s = checkS(answer);
        state.cc.cy = checkCY(answer);
        state.cc.p = checkParity(answer);
        state.b = (byte) (answer & 0xFF);
        break;
      }
      case 0x06:                                        // MVI B, D8
        state.b = opcode1;
        state.pc += 1;
        break;
      case 0x07: unimplementedInstruction(state);
      case 0x08: unimplementedInstruction(state);
      case 0x09:                                        // DAD B
      {
        int hl = appendAddr(state.h, state.l);
        int bc = appendAddr(state.b, state.c);
        int res = hl + bc;
        state.h = (byte) ((res & 0xFF00) >> 8);
        state.l = (byte) (res & 0xFF);
        state.cc.cy = (short) ((res & 0xFFFF0000) != 0 ? 1 : 0);
        break;
      }
      case 0x0a: unimplementedInstruction(state);
      case 0x0b: unimplementedInstruction(state);
      case 0x0c: unimplementedInstruction(state);
      case 0x0d:                                        // DCR C
      {
        short answer = (short) (state.c - 1);
        state.cc.z = checkZ(answer);
        state.cc.s = checkS(answer);
        state.cc.cy = checkCY(answer);
        state.cc.p = checkParity(answer);
        state.c = (byte) (answer & 0xFF);
        break;
      }
      case 0x0e:                                        // MVI C, D8
        state.c = opcode1;
        state.pc++;
        break;
      case 0x0f:                                        // RRC
      {
        byte x = state.a;
        state.a = (byte) (((x & 1) << 7) | (x >> 1));
        state.cc.cy = (short) (1 == (x&1) ? 1 : 0);
        break;
      }
      case 0x10: unimplementedInstruction(state);
      case 0x11:                                        // LXI D, D16
        state.d = opcode2;
        state.e = opcode1;
        state.pc += 2;
        break;
      case 0x12: unimplementedInstruction(state);
      case 0x13:                                        // INX D
      {
        char de = appendAddr(state.d, state.e);
        de++;
        state.d = (byte) (de >> 8 & 0xFF);
        state.e = (byte) (de & 0xFF);
        break;
      }
      case 0x14:                                        // INR D
      {
        short answer = (short) (state.d + 1);
        state.cc.z = checkZ(answer);
        state.cc.s = checkS(answer);
        state.cc.cy = checkCY(answer);
        state.cc.p = checkParity(answer);
        state.d = (byte) (answer & 0xFF);
        break;
      }
      case 0x15: unimplementedInstruction(state);
      case 0x16: unimplementedInstruction(state);
      case 0x17: unimplementedInstruction(state);
      case 0x18: unimplementedInstruction(state);
      case 0x19:                                        // DAD D
      {
        int hl = appendAddr(state.h, state.l);
        int de = appendAddr(state.d, state.e);
        int res = hl + de;
        state.h = (byte) ((res & 0xFF00) >> 8);
        state.l = (byte) (res & 0xFF);
        state.cc.cy = (short) ((res & 0xFFFF0000) != 0 ? 1 : 0);
        break;
      }
      case 0x1a:                                         // LDAX D
        state.a = state.memory[(int)appendAddr(state.d, state.e)];
        break;
      case 0x1b: unimplementedInstruction(state);
      case 0x1c: unimplementedInstruction(state);
      case 0x1d: unimplementedInstruction(state);
      case 0x1e: unimplementedInstruction(state);
      case 0x1f: unimplementedInstruction(state);
      case 0x20: unimplementedInstruction(state);
      case 0x21:                                        // LXI H, D16
        state.h = opcode2;
        state.l = opcode1;
        state.pc += 2;
        break;
      case 0x22: unimplementedInstruction(state);
      case 0x23:                                        // INX H
      {
        char hl = appendAddr(state.h, state.l);
        hl++;
        state.h = (byte) (hl >> 8 & 0xFF);
        state.l = (byte) (hl & 0xFF);
        break;
      }
      case 0x24: unimplementedInstruction(state);
      case 0x25: unimplementedInstruction(state);
      case 0x26:                                        // MOV H, D8
        state.h = opcode1;
        state.pc++;
        break;
      case 0x27: unimplementedInstruction(state);
      case 0x28: unimplementedInstruction(state);
      case 0x29:                                        // DAD H
      {
        int hl = appendAddr(state.h, state.l);
        int res = hl + hl;
        state.h = (byte) ((res & 0xFF00) >> 8);
        state.l = (byte) (res & 0xFF);
        state.cc.cy = (short) ((res & 0xFFFF0000) != 0 ? 1 : 0);
        break;
      }
      case 0x2a: unimplementedInstruction(state);
      case 0x2b: unimplementedInstruction(state);
      case 0x2c: unimplementedInstruction(state);
      case 0x2d: unimplementedInstruction(state);
      case 0x2e: unimplementedInstruction(state);
      case 0x2f: unimplementedInstruction(state);
      case 0x30: unimplementedInstruction(state);
      case 0x31:                                        // LXI SP, D16
        state.sp = appendAddr(opcode2, opcode1);
        state.pc += 2;
        break;
      case 0x32:                                        // STA addr
        state.memory[appendAddr(opcode2, opcode1)] = state.a;
        state.pc += 2;
        break;
      case 0x33: unimplementedInstruction(state);
      case 0x34: unimplementedInstruction(state);
      case 0x35: unimplementedInstruction(state);
      case 0x36:                                        // MVI M, D8
        state.memory[appendAddr(state.h, state.l)] = opcode1;
        state.pc +=1;
        break;
      case 0x37: unimplementedInstruction(state);
      case 0x38: unimplementedInstruction(state);
      case 0x39:                                        // DAD SP
      {
        int hl = appendAddr(state.h, state.l);
        int res = hl + state.sp;
        state.h = (byte) ((res & 0xFF00) >> 8);
        state.l = (byte) (res & 0xFF);
        state.cc.cy = (short) ((res & 0xFFFF0000) != 0 ? 1 : 0);
        break;
      }
      case 0x3a:                                        // LDA addr
      {
        state.a = state.memory[appendAddr(opcode2, opcode1)];
        state.pc += 2;
        break;
      }
      case 0x3b: unimplementedInstruction(state);
      case 0x3c: unimplementedInstruction(state);
      case 0x3d: unimplementedInstruction(state);
      case 0x3e:                                        // MVI A,D8
        state.a = opcode1;
        state.pc ++;
        break;
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
      case 0x56:                                        // MOV D,M
        state.d = state.memory[appendAddr(state.h, state.l)];
        break;
      case 0x57: unimplementedInstruction(state);
      case 0x58: unimplementedInstruction(state);
      case 0x59: unimplementedInstruction(state);
      case 0x5a: unimplementedInstruction(state);
      case 0x5b: unimplementedInstruction(state);
      case 0x5c: unimplementedInstruction(state);
      case 0x5d: unimplementedInstruction(state);
      case 0x5e:                                        // MOV E,M
        state.e = state.memory[appendAddr(state.h, state.l)];
        break;
      case 0x5f: unimplementedInstruction(state);
      case 0x60: unimplementedInstruction(state);
      case 0x61: unimplementedInstruction(state);
      case 0x62: unimplementedInstruction(state);
      case 0x63: unimplementedInstruction(state);
      case 0x64: unimplementedInstruction(state);
      case 0x65: unimplementedInstruction(state);
      case 0x66:                                        // MOV H,M
        state.h = state.memory[appendAddr(state.h, state.l)];
        break;
      case 0x67: unimplementedInstruction(state);
      case 0x68: unimplementedInstruction(state);
      case 0x69: unimplementedInstruction(state);
      case 0x6a: unimplementedInstruction(state);
      case 0x6b: unimplementedInstruction(state);
      case 0x6c: unimplementedInstruction(state);
      case 0x6d: unimplementedInstruction(state);
      case 0x6e: unimplementedInstruction(state);
      case 0x6f:                                         // MOV L,A
        state.l = state.a;
        break;
      case 0x70: unimplementedInstruction(state);
      case 0x71: unimplementedInstruction(state);
      case 0x72: unimplementedInstruction(state);
      case 0x73: unimplementedInstruction(state);
      case 0x74: unimplementedInstruction(state);
      case 0x75: unimplementedInstruction(state);
      case 0x76: unimplementedInstruction(state);
      case 0x77:                                         // MOV M,A
        state.memory[appendAddr(state.h, state.l) & 0xFFFF] = state.a;
        break;
      case 0x78: unimplementedInstruction(state);
      case 0x79: unimplementedInstruction(state);
      case 0x7a:                                         // MOV A,D
        state.a = state.d;
        break;
      case 0x7b:                                         // MOV A,E
        state.a = state.e;
        break;
      case 0x7c:                                         // MOV A,H
        state.a = state.h;
        break;
      case 0x7d: unimplementedInstruction(state);
      case 0x7e:                                         // MOV A,M
        state.a = state.memory[appendAddr(state.h, state.l)];
        break;
      case 0x7f: unimplementedInstruction(state);
      case 0x80:                                         // ADD B
      {
        short answer = (short) ((short)state.a + (short)state.b);
        state.cc.z = (short) (((answer & 0xFF) == 0) ? 1: 0);
        state.cc.s = (short) (((answer & 0x80) != 0) ? 1: 0);
        state.cc.cy = (short) ((answer > 0xff) ? 1: 0);
        state.cc.p = checkParity(answer);
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
        state.cc.p = checkParity(answer);
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
      case 0xa7:                                          // ANA A
      {
        state.a = (byte) (state.a & state.a);
        state.cc.cy = state.cc.ac = 0;
        state.cc.z = (short) ((state.a == 0) ? 1 : 0);
        state.cc.s = (short) (0x80 == (state.a & 0x80) ? 1 : 0);
        state.cc.p = checkParity(state.a);
        break;
      }
      case 0xa8: unimplementedInstruction(state);
      case 0xa9: unimplementedInstruction(state);
      case 0xaa: unimplementedInstruction(state);
      case 0xab: unimplementedInstruction(state);
      case 0xac: unimplementedInstruction(state);
      case 0xad: unimplementedInstruction(state);
      case 0xae: unimplementedInstruction(state);
      case 0xaf:                                          // XRA A
      {
        state.a = (byte) (state.a ^ state.a);
        state.cc.cy = state.cc.ac = 0;
        state.cc.z = (short) ((state.a == 0) ? 1 : 0);
        state.cc.s = (short) (0x80 == (state.a & 0x80) ? 1 : 0);
        state.cc.p = checkParity(state.a);
        break;
      }
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
      case 0xc1:                                          // POP B
        state.c = state.memory[state.sp];
        state.b = state.memory[state.sp+1];
        state.sp += 2;
        break;
      case 0xc2:                                          // JNZ addr
        if (state.cc.z == 0){
          state.pc = appendAddr(opcode2, opcode1);
          return 0;
        }
        else
          state.pc += 2;
        break;
      case 0xc3:                                          // JMP addr
        state.pc = appendAddr(opcode2, opcode1);
        return 0;
      case 0xc4: unimplementedInstruction(state);
      case 0xc5:                                          // PUSH B
        state.memory[state.sp - 2] = state.c;
        state.memory[state.sp - 1] = state.b;
        state.sp -= 2;
        break;
      case 0xc6:                                          // ADI D8
      {
        short answer = (short) ((short)state.a + (short)opcode1);
        state.cc.z = (short) (((answer & 0xFF) == 0) ? 1: 0);
        state.cc.s = (short) (((answer & 0x80) != 0) ? 1: 0);
        state.cc.cy = (short) ((answer > 0xff) ? 1: 0);
        state.cc.p = checkParity(answer);
        state.a = (byte) (answer & 0xFF);
        state.pc += 1;
        break;
      }
      case 0xc7: unimplementedInstruction(state);
      case 0xc8: unimplementedInstruction(state);
      case 0xc9:                                        // RET
        state.pc = appendAddr(state.memory[state.sp + 1], state.memory[state.sp]);
        state.sp = (char) (state.sp + 2);
        break;
      case 0xca: unimplementedInstruction(state);
      case 0xcb: unimplementedInstruction(state);
      case 0xcc: unimplementedInstruction(state);
      case 0xcd:                                          // CALL adr
      {
        int ret = (state.pc + 2);
        state.memory[state.sp - 1] = (byte) ((ret >> 8) & 0xFF); // sp-1 = pc.hi
        state.memory[state.sp - 2] = (byte) ((ret) & 0xFF);      // sp-2 = pc.lo
        state.sp = (char) (state.sp - 2);
        state.pc = appendAddr(opcode2, opcode1);
        return 0;
      }
      case 0xce: unimplementedInstruction(state);
      case 0xcf: unimplementedInstruction(state);
      case 0xd0:                                          // RNC
        if(state.cc.cy == 0) {
          state.pc = (char) (state.memory[state.sp] | (state.memory[state.sp + 1] << 8));
          state.sp += 2;
        }
        break;
      case 0xd1:                                          // POP D
        state.e = state.memory[state.sp];
        state.d = state.memory[state.sp+1];
        state.sp += 2;
        break;
      case 0xd2:                                          // JNC
        if(state.cc.cy == 0) {
          state.pc = (char) appendAddr(opcode2, opcode1);
          return 0;
        } else {
          state.sp += 2;
          break;
        }
      case 0xd3:                                          // OUT D8
        //TODO
        state.pc ++;
        break;
      case 0xd4:                                          // CNC adr
        if(state.cc.cy == 0) {
          int ret = state.pc + 2;
          state.memory[state.sp - 1] = (byte) ((ret >> 8) & 0xFF);
          state.memory[state.sp - 2] = (byte) (ret & 0xFF);
          state.sp -= 2;
          state.pc = appendAddr(opcode2, opcode1);
        } else {
          state.pc += 2;
        }
        break;
      case 0xd5:                                          // PUSH D
        state.memory[state.sp - 2] = state.e;
        state.memory[state.sp - 1] = state.d;
        state.sp -= 2;
        break;
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
      case 0xe1:                                        // POP H
      {
        state.l = state.memory[state.sp];
        state.h = state.memory[state.sp+1];
        state.sp += 2;
        break;
      }
      case 0xe2: unimplementedInstruction(state);
      case 0xe3: unimplementedInstruction(state);
      case 0xe4: unimplementedInstruction(state);
      case 0xe5:                                        // PUSH H
        state.memory[state.sp - 2] = state.l;
        state.memory[state.sp - 1] = state.h;
        state.sp -= 2;
        break;
      case 0xe6:                                        // ANI D8
      {
        state.a = (byte) (state.a & opcode1);
        state.cc.cy = state.cc.ac = 0;
        state.cc.z = (short) ((state.a == 0) ? 1 : 0);
        state.cc.s = (short) (0x80 == (state.a & 0x80) ? 1 : 0);
        state.cc.p = checkParity(state.a);
        state.pc++;
        break;
      }
      case 0xe7: unimplementedInstruction(state);
      case 0xe8: unimplementedInstruction(state);
      case 0xe9: unimplementedInstruction(state);
      case 0xea: unimplementedInstruction(state);
      case 0xeb:                                        // XCHG
      {
        byte temp1 = state.d;
        byte temp2 = state.e;
        state.d = state.h;
        state.e = state.l;
        state.h = temp1;
        state.l = temp2;
        break;
      }
      case 0xec: unimplementedInstruction(state);
      case 0xed: unimplementedInstruction(state);
      case 0xee: unimplementedInstruction(state);
      case 0xef: unimplementedInstruction(state);
      case 0xf0: unimplementedInstruction(state);
      case 0xf1:                                        // POP PSW
      {
        state.a = state.memory[state.sp + 1];
        byte psw = state.memory[state.sp];
        state.cc.z = (short) (0x01 == (psw & 0x01) ? 1 : 0);
        state.cc.s = (short) (0x01 == (psw & 0x02) ? 1 : 0);
        state.cc.p = (short) (0x01 == (psw & 0x04) ? 1 : 0);
        state.cc.cy = (short) (0x01 == (psw & 0x08) ? 1 : 0);
        state.cc.ac = (short) (0x01 == (psw & 0x10) ? 1 : 0);
        state.sp += 2;
        break;
      }
      case 0xf2: unimplementedInstruction(state);
      case 0xf3: unimplementedInstruction(state);
      case 0xf4: unimplementedInstruction(state);
      case 0xf5:                                        // PUSH PSW
      {
        state.memory[state.sp-1] = state.a;
        byte psw = (byte) (state.cc.z |
            state.cc.s << 1 |
            state.cc.p << 2 |
            state.cc.cy << 3 |
            state.cc.ac << 4);
        state.memory[state.sp-2] = psw;
        state.sp -= 2;
        break;
      }
      case 0xf6: unimplementedInstruction(state);
      case 0xf7: unimplementedInstruction(state);
      case 0xf8: unimplementedInstruction(state);
      case 0xf9: unimplementedInstruction(state);
      case 0xfa: unimplementedInstruction(state);
      case 0xfb:                                       // EI
        state.int_enable = 1;
        break;
      case 0xfc: unimplementedInstruction(state);
      case 0xfd: unimplementedInstruction(state);
      case 0xfe:                                       // CPI D8
      {
        byte answer  = (byte) (state.a - opcode1);
        state.cc.z = (short) (answer == 0 ? 1 : 0);
        state.cc.s = (short) (0x80 == (answer & 0x80) ? 1 : 0);
        state.cc.p = checkParity(answer);
        state.cc.cy = (short) (state.a < opcode1 ? 1 : 0);
        state.pc++;
        break;
      }
      case 0xff: unimplementedInstruction(state);
    }
    state.pc += 1;

    return 0;
  }

  private static byte checkParity(short n)
  {
    n = (short) (n & 0xFF);
    boolean parity = false;
    while(n != 0)
    {
      parity = !parity;
      n = (short) (n & (n-1));
    }
    return (byte) (parity ? 1 : 0);
  }

  private static byte checkParity(byte n)
  {
    boolean parity = false;
    while(n != 0)
    {
      parity = !parity;
      n = (byte) (n & (n-1));
    }
    return (byte) (parity ? 1 : 0);
  }

  private static char appendAddr(byte msb, byte lsb) {
    return (char) ((msb << 8) & 0xFFFF | (lsb & 0xFF));
  }

  private static void push(State8080 state, byte high, byte low) {
    state.memory[state.sp - 1] = high;
    state.memory[state.sp - 2] = low;
    state.sp -= 2;
  }

  private static short checkS(short answer) {
    return (short) (((answer & 0x80) != 0) ? 1: 0);
  }

  private static short checkZ(short answer) {
    return (short) (((answer & 0xFF) == 0) ? 1: 0);
  }

  private static short checkCY(short answer) {
    return (short) ((answer > 0xff) ? 1: 0);
  }

  public static void generateInterrupt(State8080 state, int interrupt_num) {
//    push(state, (byte)((state.pc & 0xFF00) >> 8), (byte)(state.pc & 0xFF));
//    System.out.println("========================");
//    state.pc = (char) (8*interrupt_num);
  }

  public static void printMem(int index, State8080 state) {
    byte opcode = state.memory[index];
    byte opcode1 = state.memory[index + 1];
    byte opcode2 = state.memory[index + 2];
    String flags = "";
    flags += state.cc.cy != 0 ? "CY " : "";
    flags += state.cc.z != 0 ? "Z " : "";
    flags += state.cc.s != 0 ? "S " : "";
    flags += state.cc.p != 0 ? "P " : "";
    System.out.println(String.format("%d => %04x : %02x %02x %02x a=%02x bc=%04x de=%04x hl=%04x sp=%04x flags=%s",
        instructions++, index, opcode, opcode1, opcode2, state.a, (short) appendAddr(state.b, state.c),
        (short)appendAddr(state.d, state.e), (short)appendAddr(state.h, state.l), (short)state.sp, flags));
  }
}
