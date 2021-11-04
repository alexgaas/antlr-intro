import gen.ExprBaseVisitor
import gen.ExprParser

class CalcVisitor: ExprBaseVisitor<Int>(){
    var results: HashMap<String, Int> = hashMapOf()

    override fun visitAssign(ctx: ExprParser.AssignContext): Int {
        val value = visit(ctx.expr())
        results[ctx.ID().text] = value
        return value
    }

    /** expr NEWLINE */
    override fun visitPrintExpr(ctx: ExprParser.PrintExprContext): Int {
        val value = visit(ctx.expr())
        println(value)
        return 0
    }

    /** INT */
    override fun visitInt(ctx: ExprParser.IntContext): Int {
        return ctx.INT().text.toInt()
    }

    /** ID */
    override fun visitId(ctx: ExprParser.IdContext): Int {
        return results[ctx.ID().text] ?: 0
    }

    /** expr op=('*'|'/') expr */
    override fun visitMulDiv(ctx: ExprParser.MulDivContext): Int {
        val left = visit(ctx.expr(0))
        val right = visit(ctx.expr(1))

        if (ctx.op.type == ExprParser.MUL) {
            return left * right
        }
        return left / right
    }

    /** expr op=('+'|'-') expr */
    override fun visitAddSub(ctx: ExprParser.AddSubContext): Int {
        val left = visit(ctx.expr(0))
        val right = visit(ctx.expr(1))

        if (ctx.op.type == ExprParser.ADD) {
            return left + right
        }
        return left - right
    }

    /** '(' expr ')' */
    override fun visitParens(ctx: ExprParser.ParensContext): Int {
        return visit(ctx.expr())
    }

    /** 'clear' */
    override fun visitClear(ctx: ExprParser.ClearContext): Int {
        results.clear()
        return 0
    }
}
