import java.util.*
import java.util.Stack
/*
class Tree<Double>(
    var value: Double,
    var left: Tree<Double>? = null,
    var right: Tree<Double>? = null
)*/

fun isOperator(character : Char) : Boolean
{
    return character in "+-*/"
}

fun getPrecedence(operator : Char)  : Int
{
    when (operator) {
        '+', '-' -> return 1
        '*', '/' -> return 2
    }
    return 0
}

fun polishForm(equation : String) : String
{
    val result = StringBuilder()
    val stack = Stack<Char>()
    var i = 0

    for (char in equation)
    {
        if (char.isDigit() || char == '.')
        {
            result.append(char)
            i = 1
        }
        else if (isOperator(char))
        {
            if (i != 0)
            {
                result.append(" ")
                i = 0
            }
            while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(char))
            {
                result.append(" ${stack.pop()} ")
            }
            stack.push(char)
        }
        else if (char == '(')
        {
            if (i != 0)
            {
                result.append(" ")
                i = 0
            }
            stack.push(char)
        }
        else if (char == ')')
        {
            if (i != 0)
            {
                result.append(" ")
                i = 0
            }
            while (!stack.isEmpty() && stack.peek() != '(')
            {
                result.append(" ${stack.pop()} ")
            }
            stack.pop()
        }
    }

    while (!stack.isEmpty())
    {
        result.append(" ${stack.pop()} ")
    }

    return result.toString().trim()
}

/*fun buildTree(tree : Tree<Double>, equation : String) : Tree<Double>
{
    for (char in equation)
    {
        if (char.isDigit())
        {
            tree.value = char.code.toDouble()
            if (tree.left == null)
            {
                tree.left = Tree(char.code.toDouble())
            }
            else
            {
                tree.right = Tree(char.code.toDouble())
            }
        }
        else if (isOperator(char))
        {

        }
    }
    return tree
}*/

fun evaluate(expression : String): Double
{
    val chars = expression.split(" ").filter{it != ""}
    val stack = Stack<Double>()
    var x : Double
    var y : Double

    for (char in chars)
    {
        if (char !in "+-*/" && char.toDoubleOrNull() != null)
        {
            stack.push(char.toDouble())
        }
        else
        {
            y = stack.pop()
            x = stack.pop()

            when (char)
            {
                "+" -> stack.push(x+y)
                "-" -> stack.push(x-y)
                "*" -> stack.push(x*y)
                "/" -> stack.push(x/y)
                else -> throw Exception("IT'S NOT OK!\n")
            }
        }
    }
    return stack.pop()
}

fun main()
{
    val reader = Scanner(System.`in`)
    print("Enter equation: ")
    val equation = reader.nextLine()
    val equation2 = polishForm(equation)
    println("Polish Form is $equation2")
    val result = evaluate(equation2)
    println("Result: $result")
    



    // nextDouble() reads the next double from the keyboard
 //   val first = reader.nextDouble()
    //val second = reader.nextDouble()
/*
    print("Enter an operator (+, -, *, /): ")
    val operator = reader.next()[0]

    val result: Double

    when (operator) {
        '+' -> result = first + second
        '-' -> result = first - second
        '*' -> result = first * second
        '/' -> result = first / second
        // operator doesn't match any case constant (+, -, *, /)
        else -> {
            System.out.printf("Error! operator is not correct")
            return
        }
    }

    System.out.printf("%.1f %c %.1f = %.1f", first, operator, second, result)*/
}

















