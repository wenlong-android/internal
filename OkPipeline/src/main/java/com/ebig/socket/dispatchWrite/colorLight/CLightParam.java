package com.ebig.socket.dispatchWrite.colorLight;

import com.ebig.socket.entity.TypeConstance;
import com.ebig.utils.HexUtils;

public class CLightParam {
    private final String split="";
    /*灭:0x00 , 亮:0x01*/
    private final boolean redOn ;// TypeConstance.C_00;
    private final boolean greenOn ;//TypeConstance.C_00;
    private final boolean yellowOn ;//TypeConstance.C_00;
    private final boolean buzzerOn ;//TypeConstance.C_00;
    private final long redOnMilli;
    private final long redOffMilli;
    private final long greenOnMilli;
    private final long greenOffMilli;
    private final long yellowOnMilli;
    private final long yellowOffMilli;
    private final long buzzerOnMilli;
    private final long buzzerOffMilli;
    private final long duration;

    public CLightParam(BlConfig config){
        this.redOn=config.redOn;
        this.greenOn=config.greenOn ;//TypeConstance.C_00;
        this.yellowOn=config.yellowOn ;//TypeConstance.C_00;
        this.buzzerOn=config.buzzerOn ;//TypeConstance.C_00;
        this.redOnMilli=config.redOnMilli;
        this.redOffMilli=config.redOffMilli;
        this.greenOnMilli=config.greenOnMilli;
        this.greenOffMilli=config.greenOffMilli;
        this.yellowOnMilli=config.yellowOnMilli;
        this.yellowOffMilli=config.yellowOffMilli;
        this.buzzerOnMilli=config.buzzerOnMilli;
        this.buzzerOffMilli=config.buzzerOffMilli;
        this.duration=config.duration;
    }

    public static class BlConfig {
        private boolean redOn = false;// TypeConstance.C_00;
        private boolean greenOn = false;//TypeConstance.C_00;
        private boolean yellowOn = false;//TypeConstance.C_00;
        private boolean buzzerOn = false;//TypeConstance.C_00;
        private long redOnMilli=500;
        private long redOffMilli=500;
        private long greenOnMilli=500;
        private long greenOffMilli=500;
        private long yellowOnMilli=500;
        private long yellowOffMilli=500;
        private long buzzerOnMilli=500;
        private long buzzerOffMilli=500;
        private long duration=500;

        public BlConfig() {

        }

        public BlConfig redOn(long onMilli, long offMilli) {
            this.redOn = true;
            this.redOffMilli = onMilli;
            this.redOffMilli = offMilli;
            if (redOffMilli<500)redOffMilli=500;
            if (redOffMilli<500)redOffMilli=500;
            if (redOffMilli>65535)redOffMilli=65535;
            if (redOffMilli>65535)redOffMilli=65535;

            return this;
        }

        public BlConfig redOnDefalut() {
            this.redOn = true;
            return this;
        }

        public BlConfig greenOnDefalut() {
            this.redOn = true;
            return this;
        }

        public BlConfig yellowOnDefalut() {
            this.redOn = true;
            return this;
        }

        public BlConfig greenOn(long onMilli, long offMilli){
            this.greenOn=true;
            this.greenOnMilli=onMilli;
            this.greenOffMilli=offMilli;
            if (greenOnMilli<500)greenOnMilli=500;
            if (greenOffMilli<500)greenOffMilli=500;
            if (greenOnMilli>65535)greenOnMilli=65535;
            if (greenOffMilli>65535)greenOffMilli=65535;
            return this;
        }

        public BlConfig yelloOn(long onMilli, long offMilli){
            this.yellowOn=true;
            this.yellowOnMilli=onMilli;
            this.yellowOffMilli=offMilli;
            if (yellowOnMilli<500)yellowOnMilli=500;
            if (yellowOffMilli<500)yellowOffMilli=500;
            if (yellowOnMilli>65535)yellowOnMilli=65535;
            if (yellowOffMilli>65535)yellowOffMilli=65535;
            return this;
        }
        public BlConfig buzzerOn(long onMilli, long offMilli){
            this.buzzerOn=true;
            this.buzzerOnMilli=onMilli;
            this.buzzerOffMilli=offMilli;
            if (buzzerOnMilli<500)buzzerOnMilli=500;
            if (buzzerOffMilli<500)buzzerOffMilli=500;
            if (buzzerOnMilli>65535)buzzerOnMilli=65535;
            if (buzzerOffMilli>65535)buzzerOffMilli=65535;
            return this;
        }
        public BlConfig duration(long second){
            this.duration=second;
            return this;
        }

        public CLightParam make(){
            return new CLightParam(this);
        }
    }
    public String makeCmd(){
        return getRedData()+split+ getGreenData()+split+ getYellow()+split+ getBuzzer()+split+ getDuration();
    }

    private String getRedData(){
        return (getBol(redOn))+ HexUtils.long2Hex(redOnMilli)+HexUtils.long2Hex(redOffMilli);
    }
    private String getGreenData(){
        return (getBol(greenOn))+ HexUtils.long2Hex(greenOnMilli)+HexUtils.long2Hex(greenOffMilli);
    }

    private String getYellow(){
        return (getBol(yellowOn))+ HexUtils.long2Hex(yellowOnMilli)+HexUtils.long2Hex(yellowOffMilli);
    }

    private String getBuzzer(){
        return (getBol(buzzerOn))+ HexUtils.long2Hex(buzzerOnMilli)+HexUtils.long2Hex(buzzerOffMilli);
    }

    private String getDuration(){
        return HexUtils.long2Hex(duration);
    }

    private String getBol(boolean on){
        return on? TypeConstance.C_01: TypeConstance.C_00;
    }
}
