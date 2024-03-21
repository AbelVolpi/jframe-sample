import java.awt.*
import java.awt.event.*
import javax.swing.*

fun main() {
    Calculator()
}

class Calculator : JFrame(), MouseListener, MouseMotionListener {

    private val textField1 = JTextField(10)
    private val textField2 = JTextField(10)
    private val operatorLabel = JLabel("", SwingConstants.CENTER)
    private val textFieldResult = JTextField(10)
    private var operator: Char? = null
    private val buttonPlus = JButton("+")
    private val buttonMinus = JButton("-")
    private val buttonMultiply = JButton("*")
    private val buttonDivide = JButton("/")
    private val buttonEqual = JButton("=")
    private val buttonReset = JButton("Reset")

    init {
        title = "Calculator"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(300, 200)
        layout = GridLayout(2, 4)

        // Adding mouse listeners
        val buttons = arrayOf(buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEqual, buttonReset)
        buttons.forEach { it.addMouseListener(this) }
        buttons.forEach { it.addMouseMotionListener(this) }

        // Adding action buttons
        buttonPlus.addActionListener { setOperator('+') }
        buttonMinus.addActionListener { setOperator('-') }
        buttonMultiply.addActionListener { setOperator('*') }
        buttonDivide.addActionListener { setOperator('/') }
        buttonEqual.addActionListener { calculate() }
        buttonReset.addActionListener { reset() }

        add(textField1)
        add(operatorLabel)
        add(textField2)
        add(buttonEqual)
        add(textFieldResult)
        add(buttonPlus)
        add(buttonMinus)
        add(buttonMultiply)
        add(buttonDivide)
        add(buttonReset)

        isVisible = true
    }

    private fun setOperator(op: Char) {
        operator = op
        operatorLabel.text = op.toString()
    }

    private fun calculate() {
        if (operator == null) {
            textFieldResult.text = "Operator not defined"
            return
        }

        val operand1 = textField1.text.toDouble()
        val operand2 = textField2.text.toDouble()
        val result = when (operator) {
            '+' -> operand1 + operand2
            '-' -> operand1 - operand2
            '*' -> operand1 * operand2
            '/' -> {
                if (operand2 == 0.0) {
                    textFieldResult.text = "Division by zero"
                    return
                }
                operand1 / operand2
            }

            else -> throw IllegalArgumentException("Invalid operator")
        }
        textFieldResult.text = result.toString()
    }

    private fun reset() {
        textField1.text = ""
        textField2.text = ""
        textFieldResult.text = ""
        operatorLabel.text = ""
        operator = null
    }

    override fun mouseEntered(e: MouseEvent?) {
        (e?.source as? JButton)?.apply {
            background = Color.GRAY
            setOpaque(true)
        }
    }

    override fun mouseExited(e: MouseEvent?) {
        (e?.source as? JButton)?.apply {
            background = null
            setOpaque(true)
        }
    }

    override fun mousePressed(e: MouseEvent?) {
        // Not implemented
    }

    override fun mouseReleased(e: MouseEvent?) {
        // Not implemented
    }

    override fun mouseDragged(e: MouseEvent?) {
        // Not implemented
    }

    override fun mouseMoved(e: MouseEvent?) {
        // Not implemented
    }

    override fun mouseClicked(e: MouseEvent?) {
        // Not implemented
    }
}
