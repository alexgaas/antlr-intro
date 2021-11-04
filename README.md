# ANTLR introduction
Minimal setup of ANTLR with kotlin / gradle

## Build and start

ANTLR test grammar is placed in **src/main/antlr/Expr.g4**

**`build.gradle`** task **generateGrammarSource** generates lexer/parser/listener and visitor into folder **src/main/kotlin/gen**

Visitor (**ExprBaseVisitor**) is implemented over **CalcVisitor** in **src/main/kotlin/CalcVisitor**

Test expression located in **`src/main/resources/test.expr`** as:
```
a = 5
b = 6
a+b*2
stop
a+b
stop
c = 10
d = 20
d / 2
```

Start main and you will get:
```
17
0
10
```
