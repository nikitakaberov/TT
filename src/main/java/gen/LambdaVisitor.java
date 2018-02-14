package gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LambdaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
interface LambdaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LambdaParser#let_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_expression(LambdaParser.Let_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LambdaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LambdaParser#abstraction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstraction(LambdaParser.AbstractionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LambdaParser#application}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplication(LambdaParser.ApplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LambdaParser#atomic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic(LambdaParser.AtomicContext ctx);
}