/* Copyright (c) 2011 Danish Maritime Authority
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package dk.dma.ais.reader;

import java.io.IOException;

import org.junit.Test;

import dk.dma.ais.packet.AisPacket;
import dk.dma.enav.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class AisReaderTest {

    @Test
    public void dirReaderTest() throws InterruptedException, IOException {
        AisReader reader = AisReaders.createDirectoryReader("src/test", "s*.txt", true);
        reader.registerPacketHandler(new Consumer<AisPacket>() {
            @Override
            public void accept(AisPacket packet) {
                //System.out.println("Read packet");
            }
        });
        reader.start();
        reader.join();
        assertEquals(4062L, reader.getNumberOfLinesRead());
    }

    @Test
    public void dirReaderPercentageReadTest() throws InterruptedException, IOException {
        AisDirectoryReader directoryReader = AisReaders.createDirectoryReader("src/test", "stream_example.txt", true);

        assertEquals(0L, directoryReader.getNumberOfLinesRead());
        assertEquals(0.0, directoryReader.getEstimatedFractionOfPacketsRead(), 1e-10);

        directoryReader.start();
        directoryReader.join();

        assertEquals(4051L, directoryReader.getNumberOfLinesRead());
        assertEquals(1.0, directoryReader.getEstimatedFractionOfPacketsRead(), 1e-10);
    }
}