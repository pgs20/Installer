import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Installer {
    public static void main(String[] args) {
        String path = "C:\\Users\\79265\\learn-java\\tasksNetology\\Games";

        try {
            // Установка
            String[] dirs = {"src", "res", "savegames", "temp", "src\\main", "src\\test", "res\\drawables", "res\\vectors", "res\\icons"},
                     files = {"src\\main\\Main.java", "src\\main\\Utils.java", "temp\\temp.txt"};


            StringBuilder stringBuilder = new StringBuilder();

            createDirs(dirs, stringBuilder, path);
            createFiles(files, stringBuilder, path);


            FileWriter fileWriter = new FileWriter(path + "\\temp\\temp.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();

            // Сохранение
            GameProgress gp1 = new GameProgress(100, 1, 99, 1.05);
            GameProgress gp2 = new GameProgress(79, 3, 101, 1.65);
            GameProgress gp3 = new GameProgress(90, 15, 150, 3.05);

            String[] filesSave = {"\\savegames\\save1.dat", "\\savegames\\save2.dat", "\\savegames\\save3.dat"};
            List<String> listFiles = new ArrayList<>(Arrays.asList("save1.dat", "save2.dat", "save3.dat"));
            createFiles(filesSave, stringBuilder, path);

            saveGame(path + filesSave[0], gp1);
            saveGame(path + filesSave[1], gp2);
            saveGame(path + filesSave[2], gp3);

            createFiles(new String[] {"savegames\\zip.zip"}, stringBuilder, path);

            zipFiles(path + "\\savegames\\zip.zip", listFiles);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void createDirs(String[] dirs, StringBuilder stringBuild, String path) {
        for (String dir : dirs) {
            File src = new File(path +  "\\" + dir);
            stringBuild.append((src.mkdir()) ? "Директория " + dir + " успешно создана\n" : "Ошибка! Возможно директория " + dir + " уже создана!\n");
        }
    }

    public static void createFiles(String[] files, StringBuilder stringBuild, String path) throws IOException {
        for (String file : files) {
            File src = new File(path + "\\" + file);
            stringBuild.append((src.createNewFile()) ? "Файл " + file + " успешно создан\n" : "Ошибка! Возможно файл " + file + " уже создан!\n");
        }
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try(FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameProgress);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public static void zipFiles(String pathToZip, List<String> files) {
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToZip))) {
            for (String nameFile : files) {
                try(FileInputStream fis = new FileInputStream("C:\\Users\\79265\\learn-java\\tasksNetology\\Games\\savegames\\" + nameFile)) {
                    ZipEntry entry = new ZipEntry("packed_" + nameFile);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
