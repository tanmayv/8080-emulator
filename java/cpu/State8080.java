package cpu;

public class State8080 {
  public short a, b, c, d, e, h, l;
  public int sp;
  public int pc;
  public short[] memory;
  public ConditionCodes cc;
  public short int_enable;

  public State8080(short[] memory) {
    this.memory = memory;
    this.cc = new ConditionCodes();
  }

  public State8080(byte[] memory) {
    this.memory = new short[16384];
    int index=0;
    for(byte m: memory) {
      this.memory[index] = (short) (memory[index] & 0x00FF);
      index++;
    }
    this.cc = new ConditionCodes();
  }
}
