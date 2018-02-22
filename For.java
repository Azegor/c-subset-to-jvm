import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.GOTO;
import Assembler.IFEQ;
import Assembler.LABEL;

public class For extends Statement
{
	private final Statement mInit;
	private final BoolExpr mCondition;
	private final Statement mIncr;
	private final Statement mStatement;

	public For(Statement init, BoolExpr condition, Statement incr,
			Statement statement)
	{
		mInit = init;
		mCondition = condition;
		mIncr = incr;
		mStatement = statement;
	}

	@Override
	public List<AssemblerCmd> compile(SymbolTable symbolTable)
	{
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();

		if (mInit != null)
			result.addAll(mInit.compile(symbolTable)); // init

		LABEL loopLabel = new LABEL();
		LABEL finishLabel = new LABEL();
		AssemblerCmd ifeq = new IFEQ(finishLabel.getLabel());
		AssemblerCmd gotoLoop = new GOTO(loopLabel.getLabel());
		result.add(loopLabel); // {

		if (mCondition != null)
		{
			result.addAll(mCondition.compile(symbolTable)); // condition
			result.add(ifeq); // jeq
		}

		result.addAll(mStatement.compile(symbolTable));

		if (mIncr != null)
			result.addAll(mIncr.compile(symbolTable));

		result.add(gotoLoop);

		result.add(finishLabel);
		return result;
	}

	@Override
	public Integer stackSize()
	{
		int size = 0;
		if (mInit != null)
			size = mInit.stackSize();
		if (mCondition != null)
			size = Math.max(size, mCondition.stackSize());
		if (mIncr != null)
			size = Math.max(size, mIncr.stackSize());
		return Math.max(size, mStatement.stackSize());
	}

	@Override
	public String toString()
	{
		return "For(" + mInit + ";" + mCondition + ";" + mIncr + ", "
				+ mStatement + ")";
	}
}
