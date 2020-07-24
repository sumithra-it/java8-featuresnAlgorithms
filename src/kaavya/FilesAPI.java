package kaavya;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesAPI {

public static void main(String[] args) {
// TODO Auto-generated method stub
try {
System.out.println("Test 1 - All files that are directories in the current folder are:");
Files.list(Paths.get("."))
.filter(Files :: isDirectory)
.forEach(System.out :: println);

System.out.println("Test 2 - selected files with in a given directory:");
Files.newDirectoryStream(Paths.get(".\\src\\kaavya"))//, path -> path.endsWith(".java"))
.forEach(System.out :: println);

System.out.println("Test 3 - files within nested directories as a FlatMap:");
List<File> files = 
Stream.of(new File(".").listFiles())
.flatMap(file -> file.listFiles() == null ? 
Stream.of(file) : Stream.of(file.listFiles()))
.collect(Collectors.toList());
System.out.println(files);

System.out.println("Watch for File System Changes: ");
/*A Watchable object is registered with a watch service by invoking its register method, returning a WatchKeyto represent the 
 * registration. When an event for an object is detected the key is signalled, and if not currently signalled, it is queued to the 
 * watch service so that it can be retrieved by consumers that invoke the poll or take methods to retrieve keys and process events. 
 * Once the events have been processed the consumer invokes the key's reset method to reset the key which allows the key to be signalled 
 * and re-queued with further events 
*/
final Path path = Paths.get(".");
final WatchService watchService = path.getFileSystem().newWatchService(); 
path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
System.out.println("Report any file modified within the next 5 minute:");

WatchKey watchkey = watchService.poll(5, TimeUnit.MINUTES);
if (watchkey != null) {
watchkey.pollEvents()
.stream()
.forEach(event -> System.out.println(event.context()));
}

} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}

}

//--------------------------
