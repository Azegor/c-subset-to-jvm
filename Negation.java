import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.BIPUSH;
import Assembler.ISUB;

public class Negation extends BoolExpr
{
	private BoolExpr mExpr;

	public Negation(BoolExpr expr)
	{
		mExpr = expr;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();
		AssemblerCmd bipush1 = new BIPUSH(1);
		AssemblerCmd isub = new ISUB();
		result.add(bipush1);
		result.addAll(mExpr.compile(symbolTable));
		result.add(isub);
		return result;
	}

	public Integer stackSize()
	{
		return mExpr.stackSize() + 1;
	}

	public String toString()
	{
		return "Negation(" + mExpr.toString() + ")";
	}
}
