package au.com.swivelgroup.newsapp.util

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil {
    companion object {
        val DATE_TIME_FORMAT_API = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val DATE_SHOW_FORMAT = "dd/MM/yyyy hh:mm a"

        /**
         * convert to default calendar format
         * @param dateTime
         */
        fun toCalendarDefault(dateTime: String?): Calendar? {
            return toCalendar(dateTime, DATE_TIME_FORMAT_API)
        }

        /**
         * convert date string to calendar object
         * @param dateTime
         * @param format
         */
        fun toCalendar(dateTime: String?, format: String): Calendar? {
            if (dateTime == null) return null
            val simpleDateFormat = SimpleDateFormat(format)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            try {
                val date = simpleDateFormat.parse(dateTime)
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = date.time
                Timber.d("calendar:%s", date.toString())
                return calendar
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        }

        /**
         * convert to String date
         * @param calendar
         * @param format
         */
        fun toString(calendar: Calendar?, format: String): String {
            if (calendar == null) return ""
            val simpleDateFormat = SimpleDateFormat(format)
            //simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(calendar.time)
        }

        /**
         * Get full date from the date given
         * @param dateTime
         */
        fun getFullDateDisplay(dateTime: String?): String {
            val calendar = toCalendarDefault(dateTime)
            return if (calendar != null) {
                toString(calendar, DATE_SHOW_FORMAT)
            }
            else
                ""
        }
    }
}