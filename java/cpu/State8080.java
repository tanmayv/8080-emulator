package cpu;

public class State8080 {
  public byte a, b, c, d, e, h, l;
  public int sp;
  public int pc;
  public byte[] memory;
  public ConditionCodes cc;
  public short int_enable;

  public State8080(byte[] memory) {
    this.memory = new byte[8000*8];
    int index = 0;
    for(byte b: memory) {
      this.memory[index] = memory[index];
      index ++;
    }
    this.cc = new ConditionCodes();
    this.sp = 0xf000;
  }
}
