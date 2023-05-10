import org.testng.annotations.*;

//import java.util.UUID;
//
 public class RenamePlaylistTest extends BaseTest{
// Prerequisite - at least one user-created playlist

    @Test
    public void renamePlaylist(){
        login("demo@class.com", "te$t$tudent");
        doubleClickPlaylist();
        enterNewPlayListName();
        //Assert.assertTrue((doesPlaylistExist()));
    }

}
