/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.openmuc.jmbus.transportlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

class SerialLayer implements TransportLayer {
    private final SerialBuilder serialPortBuilder;
    private final int timeout;

    private DataOutputStream os;
    private DataInputStream is;
    private SerialPort serialPort;

    public SerialLayer(int timeout, SerialBuilder serialPortBuilder) {
        this.serialPortBuilder = serialPortBuilder;
        this.timeout = timeout;
    }

    @Override
    public void open() throws IOException {
        serialPort = SerialPort.getCommPort(serialPortBuilder.getSerialPortName()); 

        serialPort.setComPortParameters(serialPortBuilder.getBaudrate(), serialPortBuilder.getDataBits(), 
                serialPortBuilder.getStopBits(), serialPortBuilder.getParity()); // Set your parameters
        serialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, timeout, timeout);


        os = new DataOutputStream(serialPort.getOutputStream());
        is = new DataInputStream(serialPort.getInputStream());
    }

    @Override
    public DataOutputStream getOutputStream() {
        return os;
    }

    @Override
    public DataInputStream getInputStream() {
        return is;
    }

    @Override
    public void close() {
        if (serialPort == null || !serialPort.isOpen()) {
            return;
        }
        serialPort.closePort();

    }

    @Override
    public boolean isClosed() {
        return serialPort == null;
    }

    @Override
    public void setTimeout(int timeout) throws IOException {
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, timeout, timeout);

    }

    @Override
    public int getTimeout() {
        return serialPort.getReadTimeout();
    }

}
