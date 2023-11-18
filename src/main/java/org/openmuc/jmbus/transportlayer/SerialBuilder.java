/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.openmuc.jmbus.transportlayer;
import com.fazecast.jSerialComm.SerialPort;

/**
 * Connection builder for serial connections.
 */
public abstract class SerialBuilder<T, S extends SerialBuilder<T, S>> extends Builder<T, S> {

    /**
     * @return the serialPortName
     */
    public String getSerialPortName() {
        return serialPortName;
    }

    /**
     * @return the baudrate
     */
    public int getBaudrate() {
        return baudrate;
    }

    /**
     * @return the dataBits
     */
    public int getDataBits() {
        return dataBits;
    }

    /**
     * @return the stopBits
     */
    public int getStopBits() {
        return stopBits;
    }

    /**
     * @return the parity
     */
    public int getParity() {
        return parity;
    }

    private String serialPortName;
    private int baudrate;
    private int dataBits;
    private int stopBits;
    private int parity;

    /**
     * Constructor of the Serial Settings Builder, for connecting M-Bus devices over serial connections like RS232 and
     * RS485. With default settings.
     * 
     * @param serialPortName
     *            examples for serial port identifiers are on Linux "/dev/ttyS0" or "/dev/ttyUSB0" and on Windows "COM1"
     **/
    protected SerialBuilder(String serialPortName) {
        this.serialPortName = serialPortName;

        this.baudrate = 2400;
        this.dataBits = 8;
        this.stopBits = SerialPort.ONE_STOP_BIT;
        this.parity = SerialPort.EVEN_PARITY;
    }

    /**
     * Sets the baudrate of the device
     * 
     * @param baudrate
     *            the baud rate to use.
     * @return the builder itself
     */
    public S setBaudrate(int baudrate) {
        this.baudrate = baudrate;
        return self();
    }

    /**
     * Sets the serial port name of the device
     * 
     * @param serialPortName
     *            examples for serial port identifiers are on Linux {@code "/dev/ttyS0"} or {@code "/dev/ttyUSB0"} and
     *            on Windows {@code "COM1"}.
     * @return the builder itself.
     */
    public S setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
        return self();
    }

    /**
     * Sets the number of DataBits, default is {@link DataBits#DATABITS_8}.
     * 
     * @param dataBits
     *            the new number of databits.
     * @return the builder itself.
     */
    public S setDataBits(int dataBits) {
        this.dataBits = dataBits;
        return self();
    }

    /**
     * Sets the stop bits, default is 1
     * 
     * @param stopBits
     *            Possible values are 1, 1.5 or 2
     * @return the builder itself
     */
    public S setStopBits(int stopBits) {
        this.stopBits = stopBits;
        return self();
    }

    /**
     * Sets the parity, default is NONE
     * 
     * @param parity
     *            Possible values are NONE, EVEN, ODD, SPACE or MARK.
     * @return the builder itself
     */
    public S setParity(int parity) {
        this.parity = parity;
        return self();
    }

    @Override
    protected TransportLayer buildTransportLayer() {
        return new SerialLayer(getTimeout(), this);
    }

}
