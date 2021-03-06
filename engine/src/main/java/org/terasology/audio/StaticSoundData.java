/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.audio;

import org.terasology.asset.AssetData;

import java.nio.ByteBuffer;

/**
 * The information used to create Sound assets.
 */
public class StaticSoundData implements AssetData {
    private ByteBuffer data;
    private int channels;
    private int sampleRate;
    private int bufferBits;

    public StaticSoundData(ByteBuffer soundData, int channels, int sampleRate, int bufferBits) {
        this.data = soundData;
        this.channels = channels;
        this.sampleRate = sampleRate;
        this.bufferBits = bufferBits;
    }

    public ByteBuffer getData() {
        return data;
    }

    public int getChannels() {
        return channels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getBufferBits() {
        return bufferBits;
    }
}
