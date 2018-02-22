import java.util.List;

import Assembler.AssemblerCmd;

public abstract class BoolExpr
{
	public abstract List<AssemblerCmd> compile(SymbolTable symbolTable);

	public abstract Integer stackSize(); // maximum size of stack needed
}
