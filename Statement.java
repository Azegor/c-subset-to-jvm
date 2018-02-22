import java.util.List;

import Assembler.AssemblerCmd;

/**
 * This class implements various forms of statements. It is the superclass of
 * the following classes: Assign Block Declaration ExprStatement IfThen
 * IfThenElse Return While
 */
public abstract class Statement
{
	public abstract List<AssemblerCmd> compile(SymbolTable symbolTable);

	public abstract Integer stackSize(); // maximum size of stack needed
}
