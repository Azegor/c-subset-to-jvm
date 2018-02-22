import java.util.ArrayList;
import java.util.List;

import Assembler.AssemblerCmd;
import Assembler.END_METHOD;
import Assembler.LIMIT;
import Assembler.MAIN;
import Assembler.METHOD;
import Assembler.NEWLINE;
import Assembler.RETURN;

public class Function
{
	private final String mName;
	private final List<String> mParameterList;
	private final List<Declaration> mDeclarations;
	private final List<Statement> mBody;

	private final Integer mLocals; // number of local variables

	public Function(String name,
			List<String> parameterList,
			List<Declaration> declarations,
			List<Statement> body)
	{
		mName = name;
		mParameterList = parameterList;
		mDeclarations = declarations;
		mBody = body;
		mLocals = mParameterList.size() + mDeclarations.size();
	}

	public List<AssemblerCmd> compile()
	{
		SymbolTable symbolTable = new SymbolTable();
		Integer count = 0;
		for (String var : mParameterList)
		{
			symbolTable.put(var, count);
			++count;
		}
		for (Declaration decl : mDeclarations)
		{
			symbolTable.put(decl.getVar(), count);
			++count;
		}
		Integer stackSize = 0;
		for (Declaration dec : mDeclarations)
		{
			stackSize = Math.max(stackSize, dec.stackSize());
		}

		for (Statement stmnt : mBody)
		{
			stackSize = Math.max(stackSize, stmnt.stackSize());
		}
		List<AssemblerCmd> result = new ArrayList<AssemblerCmd>();
		AssemblerCmd nl = new NEWLINE();
		result.add(nl);
		if (mName.equals("main"))
		{
			AssemblerCmd main = new MAIN();
			AssemblerCmd limitLocals = new LIMIT("locals", mLocals);
			AssemblerCmd limitStack = new LIMIT("stack", stackSize);
			result.add(main);
			result.add(limitLocals);
			result.add(limitStack);
			for (Declaration dec : mDeclarations)
			{
				result.addAll(dec.compile(symbolTable));
			}
			for (Statement stmnt : mBody)
			{
				result.addAll(stmnt.compile(symbolTable));
			}
			AssemblerCmd myReturn = new RETURN();
			AssemblerCmd endMain = new END_METHOD();
			result.add(myReturn);
			result.add(endMain);
		}
		else
		{
			AssemblerCmd method = new METHOD(mName, mParameterList.size());
			AssemblerCmd limitLocals = new LIMIT("locals", mLocals);
			AssemblerCmd limitStack = new LIMIT("stack", stackSize);
			result.add(method);
			result.add(limitLocals);
			result.add(limitStack);
			for (Declaration dec : mDeclarations)
			{
				result.addAll(dec.compile(symbolTable));
			}
			for (Statement stmnt : mBody)
			{
				result.addAll(stmnt.compile(symbolTable));
			}
			AssemblerCmd endMethod = new END_METHOD();
			result.add(endMethod);
		}
		return result;
	}

	// size of stack needed
	public Integer stackSize()
	{
		Integer biggest = 0;
		for (Statement stmnt : mBody)
		{
			biggest = Math.max(biggest, stmnt.stackSize());
		}
		return biggest;
	}

	@Override
	public String toString()
	{
		return "Function(" + mName + ", " + mParameterList + ", " + mBody + ")";
	}
}
