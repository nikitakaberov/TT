package gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LambdaParser}.
 */
interface LambdaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LambdaParser#let_expression}.
	 * @param ctx the parse tree
	 */
	void enterLet_expression(LambdaParser.Let_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#let_expression}.
	 * @param ctx the parse tree
	 */
	void exitLet_expression(LambdaParser.Let_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LambdaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LambdaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#abstraction}.
	 * @param ctx the parse tree
	 */
	void enterAbstraction(LambdaParser.AbstractionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#abstraction}.
	 * @param ctx the parse tree
	 */
	void exitAbstraction(LambdaParser.AbstractionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#application}.
	 * @param ctx the parse tree
	 */
	void enterApplication(LambdaParser.ApplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#application}.
	 * @param ctx the parse tree
	 */
	void exitApplication(LambdaParser.ApplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#atomic}.
	 * @param ctx the parse tree
	 */
	void enterAtomic(LambdaParser.AtomicContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#atomic}.
	 * @param ctx the parse tree
	 */
	void exitAtomic(LambdaParser.AtomicContext ctx);
}