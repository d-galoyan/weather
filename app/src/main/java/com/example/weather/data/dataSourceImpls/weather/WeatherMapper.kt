package com.example.weather.data.dataSourceImpls.weather

import com.example.weather.data.db.weather.DailyWeatherEntity
import com.example.weather.data.db.weather.DailyWithHourly
import com.example.weather.data.db.weather.HourlyWeatherEntity
import com.example.weather.data.db.weather.WeatherEntity
import com.example.weather.data.db.weather.WeatherWithHourlyAndDaily
import com.example.weather.data.utils.DateTimeUtils
import com.example.weather.domain.models.Hourly
import com.example.weather.domain.models.DayWeather
import com.example.weather.domain.models.Weather
import com.example.weather.domain.models.WeatherShort
import java.time.LocalDate
import java.time.LocalDateTime

fun WeatherDataModel.toWeather(cityName: String): Weather {

    val nextDays = mutableListOf<DayWeather>()

    for (i in daily.day.indices) {
        with(daily) {
            nextDays.add(
                DayWeather(
                    day = day[i],
                    dailyMax = maxTemp[i].toInt(),
                    nightMax = minTemp[i].toInt(),
                    windSpeed = windSpeed[i],
                    sunrise = sunrise[i],
                    sunset = sunset[i],
                    weatherCode = weatherCode[i],
                    hourly = hourly.time.indices.filter { index ->
                        val day = LocalDate.parse(day[i])
                        val dateTime = LocalDateTime.parse(hourly.time[index])
                        day.dayOfMonth == dateTime.dayOfMonth
                    }.map {
                        Hourly(
                            time = hourly.time[it],
                            temp = hourly.temp[it].toInt(),
                            apparentTemp = hourly.apparentTemp[it].toInt(),
                            humidity = hourly.humidity[it],
                            pressure = hourly.pressure[it],
                            weatherCode = hourly.weatherCode[it]
                        )
                    }
                )
            )
        }
    }

    val today = nextDays[0]

    return Weather(
        id = "$lat$long",
        cityName = cityName,
        dailyMax = today.dailyMax,
        nightMax = today.nightMax,
        lat = lat,
        long = long,
        currentTemperature = currentWeather.temp.toInt(),
        apparentTemp = today.hourly[0].apparentTemp,
        tempUnit = unit.tempUnit,
        humidityUnit = unit.humidityUnit,
        pressureUnit = unit.pressureUnit,
        days = nextDays,
        windSpeedUnit = dailyUnits.windSpeedUnit,
    )
}

fun WeatherEntity.toShortWeather(): WeatherShort {
    return WeatherShort(
        id = id,
        cityName = cityName,
        dailyMax = dailyMax,
        nightMax = nightMax,
        lat = lat,
        long = lng,
        tempUnit = tempUnit,
        weatherCode = weatherCode
    )
}

fun Weather.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        id = id,
        cityName = cityName,
        lat = lat,
        lng = long,
        dailyMax = dailyMax,
        nightMax = nightMax,
        currentTemp = currentTemperature.toFloat(),
        windSpeed = days[0].hourly[0].apparentTemp.toFloat(),
        tempUnit = tempUnit,
        humidityUnit = humidityUnit,
        pressureUnit = pressureUnit,
        windSpeedUnit = windSpeedUnit,
        weatherCode = days[0].weatherCode
    )
}

fun Weather.toWeatherShort(): WeatherShort {
    return WeatherShort(
        id = id,
        cityName = cityName,
        lat = lat,
        long = long,
        dailyMax = dailyMax,
        nightMax = nightMax,
        tempUnit = tempUnit,
        weatherCode = days[0].weatherCode
    )
}

fun DayWeather.toWeatherDailyEntity(weatherId: String): DailyWeatherEntity {
    return DailyWeatherEntity(
        weather_id = weatherId,
        day = day,
        maxTemp = dailyMax,
        minTemp = nightMax,
        windSpeed = windSpeed,
        sunrise = sunrise,
        sunset = sunset,
        weatherCode = weatherCode
    )
}

fun Hourly.toWeatherHourlyEntity(dayId: Long): HourlyWeatherEntity {
    return HourlyWeatherEntity(
        day_id = dayId,
        time = time,
        temp = temp.toFloat(),
        apparentTemp = apparentTemp.toFloat(),
        humidity = humidity,
        pressure = pressure,
        weatherCode = weatherCode
    )
}

fun HourlyWeatherEntity.toHourly(): Hourly {
    return Hourly(
        time = DateTimeUtils(time).getTime(),
        temp = temp.toInt(),
        apparentTemp = apparentTemp.toInt(),
        humidity = humidity,
        pressure = pressure,
        weatherCode = weatherCode
    )
}

fun DailyWithHourly.toNextDay(): DayWeather {
    return DayWeather(
        day = daily.day,
        dailyMax = daily.maxTemp,
        nightMax = daily.minTemp,
        windSpeed = daily.windSpeed,
        sunrise = DateTimeUtils(daily.sunrise).getTime(),
        sunset = DateTimeUtils(daily.sunset).getTime(),
        hourly = hourly.map { it.toHourly() },
        weatherCode = daily.weatherCode
    )
}

fun WeatherWithHourlyAndDaily.toWeather(): Weather {
    return Weather(
        id = weather.id,
        cityName = weather.cityName,
        dailyMax = weather.dailyMax,
        nightMax = weather.nightMax,
        lat = weather.lat,
        long = weather.lng,
        currentTemperature = weather.currentTemp.toInt(),
        apparentTemp = daily[0].hourly[0].apparentTemp.toInt(),
        tempUnit = weather.tempUnit,
        humidityUnit = weather.humidityUnit,
        pressureUnit = weather.pressureUnit,
        windSpeedUnit = weather.windSpeedUnit,
        days = daily.map { it.toNextDay() }
    )
}