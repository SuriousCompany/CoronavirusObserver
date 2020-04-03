package company.surious.coronavirusobserver.presentation.utils

object TextUtils {
    fun addNumberDots(number: String): String =
        if (number.length > 3) {
            val segments = ArrayList<String>()
            val stringBuilder = StringBuilder()
            val length = number.length
            for (i in length - 1 downTo 0) {
                stringBuilder.append(number[i])
                if ((length - i) % 3 == 0) {
                    stringBuilder.reverse()
                    segments.add(stringBuilder.toString())
                    stringBuilder.clear()
                }
            }
            stringBuilder.reverse()
            if (stringBuilder.isNotEmpty()) {
                segments.add(stringBuilder.toString())
            }
            stringBuilder.clear()
            segments.reverse()
            segments.forEachIndexed { index, segment ->
                stringBuilder.append(segment)
                if (index != segments.size - 1) {
                    stringBuilder.append('.')
                }
            }
            stringBuilder.toString()
        } else {
            number
        }
}