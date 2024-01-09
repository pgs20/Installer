import java.io.*;

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

}
