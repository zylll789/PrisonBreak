package bin.util;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Save {

    static String base = "\\save";
    String path = Objects.requireNonNull(this.getClass().getResource("/")).getPath();

    private static boolean shouldSave = false;

    private static void saveAttrNum(int[] attrNum, XMLStreamWriter writer) throws XMLStreamException {
        writeElement("health", ((Integer) attrNum[0]).toString(), writer);
        writeElement("energy", ((Integer) attrNum[1]).toString(), writer);
        writeElement("weapon", ((Integer) attrNum[2]).toString(), writer);
        writeElement("money", ((Integer) attrNum[3]).toString(), writer);
        writeElement("food", ((Integer) attrNum[4]).toString(), writer);
    }

    private static void saveWeight(String name, int[] weight, XMLStreamWriter writer) throws XMLStreamException {
        writeElement(name + "a", ((Integer) weight[0]).toString(), writer);
        writeElement(name + "s", ((Integer) weight[1]).toString(), writer);
        writeElement(name + "d", ((Integer) weight[2]).toString(), writer);
        writeElement(name + "f", ((Integer) weight[3]).toString(), writer);
        writeElement(name + "g", ((Integer) weight[4]).toString(), writer);
        writeElement(name + "h", ((Integer) weight[5]).toString(), writer);
        writeElement(name + "j", ((Integer) weight[6]).toString(), writer);
        writeElement(name + "k", ((Integer) weight[7]).toString(), writer);
        writeElement(name + "l", ((Integer) weight[8]).toString(), writer);
    }

    private static void saveMap(int[][] map, XMLStreamWriter writer) throws XMLStreamException {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 5; ++j) {
                writeElement("M" + i + j, ((Integer) map[i][j]).toString(), writer);
            }
        }
    }

    private static String testExist(String name, String path, ResourceBundle lang) {
        File file = new File(path + "\\" + name + ".xml");
        if (file.exists()) {
            System.out.println(lang.getString("save_same"));
            Scanner scanner = new Scanner(System.in);
            String temp = scanner.next();
            if (temp.equals("2")) {
                System.out.println(lang.getString("rename"));
                temp = scanner.next();
                return testExist(temp, path, lang);
            } else if (!temp.equals("1")) {
                System.out.println(lang.getString("not_save"));
                return null;
            } else {
                System.out.println(lang.getString("override_save"));
            }
        }
        shouldSave = true;
        return path + "\\" + name + ".xml";
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void writeXML(String root, int[] attrNum, String playerType, ResourceBundle lang, Integer floor, Integer packageLimit, Integer maxHealth, Integer maxEnergy, Integer starve, Boolean visible, Boolean canInvisible, Boolean ifContinue, Boolean goUp, int[] originalWeight, int[] weight, int[] weightOperator, int[][] map, Integer playP) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        File file1 = new File(path + base);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String filename = testExist(root, path + base, lang);
        System.out.println("debug");
        if (shouldSave) {
            shouldSave = false;
            assert filename != null;
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(filename), "UTF-8");
                writer.writeStartDocument("UTF-8", "1.0");
                enter(writer);
                writer.writeStartElement("data");
                enter(writer);
                writer.writeStartElement("play");
                writeElement("player", playerType, writer);
                saveAttrNum(attrNum, writer);
                if (lang.equals(ResourceBundle.getBundle("lib.lang.lang", Locale.CHINA))) {
                    writeElement("lang", "cn", writer);
                } else {
                    writeElement("lang", "us", writer);
                }
                writeElement("floor", floor.toString(), writer);
                writeElement("packageLimit", packageLimit.toString(), writer);
                writeElement("maxHealth", maxHealth.toString(), writer);
                writeElement("maxEnergy", maxEnergy.toString(), writer);
                writeElement("starve", starve.toString(), writer);
                writeElement("visible", visible.toString(), writer);
                writeElement("ifContinue", ifContinue.toString(), writer);
                writeElement("canInvisible", canInvisible.toString(), writer);
                writeElement("goUp", goUp.toString(), writer);
                saveWeight("originalWeight", originalWeight, writer);
                saveWeight("weight", weight, writer);
                saveWeight("weightOperator", weightOperator, writer);
                saveMap(map, writer);
                writeElement("playP", playP.toString(), writer);
                writer.writeEndElement();
                enter(writer);
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.flush();
                writer.close();
                System.out.println(lang.getString("save"));
            } catch (XMLStreamException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeElement(String name, String value, XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute(name, value);
    }

    private static void enter(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeCharacters("\n");
    }

}
