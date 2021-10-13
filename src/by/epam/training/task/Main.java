package by.epam.training.task;

import by.epam.training.task.total.Total;
import by.epam.training.task.devices.Device;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILENAME = "ForImport.xml";
    private static final double EXCHANGE_RATE_USD = 2.5;
    private static final double EXCHANGE_RATE_EUR = 2.9;

    public static void main(String[] args) {

        double totalPrice = 0.0;
        int totalWeight = 0;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("device");

            List<Device> deviceList = new ArrayList<>();

            for (int i = 0; i < list.getLength(); i++) {
                double priceEquivalent = 0.0;
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element deviceElement = (Element) list.item(i);

                    Device device = new Device();

                    device.setType(deviceElement.getElementsByTagName("type").item(0).getTextContent());
                    device.setName(deviceElement.getElementsByTagName("name").item(0).getTextContent());
                    device.setCurrency(deviceElement.getElementsByTagName("currency").item(0).getTextContent());
                    device.setPrice(Double.parseDouble(deviceElement.getElementsByTagName("price").item(0).getTextContent()));
                    device.setMeasure(deviceElement.getElementsByTagName("measure").item(0).getTextContent());
                    device.setWeight(Integer.parseInt(deviceElement.getElementsByTagName("weight").item(0).getTextContent()));

                    switch (device.getCurrency()) {

                        case "USD": {
                            priceEquivalent = device.getPrice() * EXCHANGE_RATE_USD;
                        }
                        break;

                        case "EUR": {
                            priceEquivalent = device.getPrice() * EXCHANGE_RATE_EUR;
                        }
                        break;

                        case "BYN": {
                            priceEquivalent = device.getPrice();
                        }
                        break;
                    }

                    totalPrice = totalPrice + priceEquivalent;
                    totalWeight = totalWeight + device.getWeight();

                    deviceList.add(device);
                }
            }

//            deviceList.forEach(System.out::println);

            for (int j = 0; j < deviceList.size(); j++) {
                System.out.print("Device" + (j + 1) + ": ");
                System.out.print(deviceList.get(j).getType() + ", ");
                System.out.print(deviceList.get(j).getName() + ", ");
                System.out.print(deviceList.get(j).getPrice() + " ");
                System.out.print(deviceList.get(j).getCurrency() + ", ");
                System.out.print(deviceList.get(j).getWeight() + " ");
                System.out.println(deviceList.get(j).getMeasure());
            }

            try {
                Total total = new Total(totalPrice, totalWeight);

                FileOutputStream export = new FileOutputStream(new File("./exported.xml"));
                XMLEncoder encoder = new XMLEncoder(export);
                for (Device device : deviceList) {
                    encoder.writeObject(device);
                }
                encoder.writeObject(total);
                encoder.close();
                export.close();


            } catch (IOException ex) {
                ex.printStackTrace();
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        System.out.println("Total Price = " + totalPrice + " BYN");
        System.out.println("Total Weight = " + totalWeight + " gram");

    }
}
