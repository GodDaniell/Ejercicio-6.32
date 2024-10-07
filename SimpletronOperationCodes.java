
/**
 * @author Kurt P
 * @version 1.0.0.01082013
 */
public abstract class SimpletronOperationCodes {
    //Read and write operations
    protected static final int READ = 10;
    protected static final int WRITE = 11;

    //Load and store operations
    protected static final int LOAD = 20;
    protected static final int STORE = 21;

    //Arithmetic operations
    protected static final int ADD = 30;
    protected static final int SUBTRACT = 31;
    protected static final int DIVIDE = 32;
    protected static final int MULITPLY = 33;

    //Modifications for b) and c)
    protected static final int MOD = 45;  // Instrucción para residuo (mod)
    protected static final int EXPONENTIATION = 44;  // Instrucción para exponenciación

    //Transfer of control operations
    protected static final int BRANCH = 40;
    protected static final int BRANCHNEG = 41;
    protected static final int BRANCHZERO = 42;
    protected static final int HALT = 43;

    //Logical operations
    protected static final int AND = 50;
    protected static final int OR = 51;
    protected static final int XOR = 52;
}

