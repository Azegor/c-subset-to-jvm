import java.util.List;

import Assembler.AssemblerCmd;

/**
 * This class represents an expression and is the parent class of the following
 * classes: Sum Difference Product Quotient FunctionCall MyNumber Variable
 */
public abstract class Expr
{
	public abstract List<AssemblerCmd> compile(SymbolTable symbolTable);

	public abstract Integer stackSize(); // maximum size of stack needed
}
