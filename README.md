# Jmeterplugin-datasketches

Most of the JMeter listeners are failing to report the distribution of measured response times. In general only mean, minimum and maximum times are measured.

Some listeners using graphics are available when a histogram or quantiles are needed. But these plugins are know to use a lot of memory because they keep all measured values to calculate the distribution when the load test has finished.

This listener plugin uses the [DataSketches](https://datasketches.apache.org/) library to implement a low overhead calculation of quantiles and histograms even for high transaction rates.

## Installation

This is currently work in progress and not ready for prime time.
