package bin.util;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Read {

    public String playerType;
    public ResourceBundle lang;
    public int[] attrNum = new int[5];
    public int floor;
    public int packageLimit;
    public int maxHealth;
    public int maxEnergy;
    public int[] originalWeight = new int[9];
    public int[] weight = new int[9];
    public int[] weightOperator = new int[9];
    public boolean visible;
    public boolean canInvisible;
    public boolean ifContinue;
    public int starve;
    public boolean goUp;
    public int[][] map = new int[3][5];
    public int playP;


    String path = ".\\" + "\\save";

    public int readXML(String filename, ResourceBundle lang) {
        File dic = new File(path);
        if (!dic.exists()) {
            System.out.println(lang.getString("read_empty"));
            return -1;
        }
        File file = new File(path + filename);
        if (!file.exists()) {
            System.out.println(lang.getString("read_file_empty"));
            return -1;
        }
        try {
            XMLStreamReader reader = XMLInputFactory.newFactory().createXMLStreamReader(new FileInputStream(file));
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    if ("play".equals(reader.getLocalName())) {
                        this.playerType = reader.getAttributeValue(null, "player");
                        this.attrNum[0] = Integer.parseInt(reader.getAttributeValue(null, "health"));
                        this.attrNum[1] = Integer.parseInt(reader.getAttributeValue(null, "energy"));
                        this.attrNum[2] = Integer.parseInt(reader.getAttributeValue(null, "weapon"));
                        this.attrNum[3] = Integer.parseInt(reader.getAttributeValue(null, "money"));
                        this.attrNum[4] = Integer.parseInt(reader.getAttributeValue(null, "food"));
                        String lang1 = reader.getAttributeValue(null, "lang");
                        if (lang1.equals("cn")) {
                            this.lang = ResourceBundle.getBundle("lib.lang.lang", Locale.CHINA);
                        } else {
                            this.lang = ResourceBundle.getBundle("lib.lang.lang", Locale.US);
                        }
                        this.floor = Integer.parseInt(reader.getAttributeValue(null, "floor"));
                        this.packageLimit = Integer.parseInt(reader.getAttributeValue(null, "packageLimit"));
                        this.maxHealth = Integer.parseInt(reader.getAttributeValue(null, "maxHealth"));
                        this.maxEnergy = Integer.parseInt(reader.getAttributeValue(null, "maxEnergy"));
                        this.starve = Integer.parseInt(reader.getAttributeValue(null, "starve"));
                        this.visible = Boolean.parseBoolean(reader.getAttributeValue(null, "visible"));
                        this.canInvisible = Boolean.parseBoolean(reader.getAttributeValue(null, "canInvisible"));
                        this.ifContinue = Boolean.parseBoolean(reader.getAttributeValue(null, "ifContinue"));
                        this.goUp = Boolean.parseBoolean(reader.getAttributeValue(null, "goUp"));
                        this.originalWeight[0] = Integer.parseInt(reader.getAttributeValue(null, "originalWeighta"));
                        this.originalWeight[1] = Integer.parseInt(reader.getAttributeValue(null, "originalWeights"));
                        this.originalWeight[2] = Integer.parseInt(reader.getAttributeValue(null, "originalWeightd"));
                        this.originalWeight[3] = Integer.parseInt(reader.getAttributeValue(null, "originalWeightf"));
                        this.originalWeight[4] = Integer.parseInt(reader.getAttributeValue(null, "originalWeightg"));
                        this.originalWeight[5] = Integer.parseInt(reader.getAttributeValue(null, "originalWeighth"));
                        this.originalWeight[6] = Integer.parseInt(reader.getAttributeValue(null, "originalWeightj"));
                        this.originalWeight[7] = Integer.parseInt(reader.getAttributeValue(null, "originalWeightk"));
                        this.originalWeight[8] = Integer.parseInt(reader.getAttributeValue(null, "originalWeightl"));
                        this.weight[0] = Integer.parseInt(reader.getAttributeValue(null, "weighta"));
                        this.weight[1] = Integer.parseInt(reader.getAttributeValue(null, "weights"));
                        this.weight[2] = Integer.parseInt(reader.getAttributeValue(null, "weightd"));
                        this.weight[3] = Integer.parseInt(reader.getAttributeValue(null, "weightf"));
                        this.weight[4] = Integer.parseInt(reader.getAttributeValue(null, "weightg"));
                        this.weight[5] = Integer.parseInt(reader.getAttributeValue(null, "weighth"));
                        this.weight[6] = Integer.parseInt(reader.getAttributeValue(null, "weightj"));
                        this.weight[7] = Integer.parseInt(reader.getAttributeValue(null, "weightk"));
                        this.weight[8] = Integer.parseInt(reader.getAttributeValue(null, "weightl"));
                        this.weightOperator[0] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatora"));
                        this.weightOperator[1] = Integer.parseInt(reader.getAttributeValue(null, "weightOperators"));
                        this.weightOperator[2] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatord"));
                        this.weightOperator[3] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatorf"));
                        this.weightOperator[4] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatorg"));
                        this.weightOperator[5] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatorh"));
                        this.weightOperator[6] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatorj"));
                        this.weightOperator[7] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatork"));
                        this.weightOperator[8] = Integer.parseInt(reader.getAttributeValue(null, "weightOperatorl"));
                        for (int i = 0; i < 3; ++i) {
                            for (int j = 0; j < 5; ++j) {
                                this.map[i][j] = Integer.parseInt(reader.getAttributeValue(null, "M"+ i + j));
                            }
                        }
                        this.playP = Integer.parseInt(reader.getAttributeValue(null, "playP"));
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
