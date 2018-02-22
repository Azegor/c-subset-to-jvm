import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.IFEQ;
import Assembler.LABEL;

public class IfThen extends Statement
{
	private BoolExpr mBoolExpr;
	private Statement mStatement;

	public IfThen(BoolExpr boolExpr, Statement statement)
	{
		mBoolExpr = boolExpr;
		mStatement = statement;
	}

	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = mBoolExpr.compile(symbolTable);
		LABEL elseLabel = new LABEL();
		AssemblerCmd ifeq = new IFEQ(elseLabel.getLabel());
		result.add(ifeq);
		result.addAll(mStatement.compile(symbolTable));
		result.add(elseLabel);
		return result;
	}

	public Integer stackSize()
	{
		return Math.max(mBoolExpr.stackSize(), mStatement.stackSize());
	}

	public String toString()
	{
		return "IfThen(" + mBoolExpr + ", " + mStatement + ")";
	}
}
