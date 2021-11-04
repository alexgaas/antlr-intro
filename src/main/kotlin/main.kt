import gen.ExprLexer
import gen.ExprParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.lang.Thread.currentThread

fun main() {
    // load calc expression from resources folder
    val loader = currentThread().contextClassLoader
    val inputStream = loader.getResourceAsStream("test.expr")
    val input = CharStreams.fromStream(inputStream)
    // parse
    val lexer = ExprLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = ExprParser(tokens)
    val tree = parser.prog()
    // visit calc expression and output result
    val eval = CalcVisitor()
    eval.visit(tree)
}
