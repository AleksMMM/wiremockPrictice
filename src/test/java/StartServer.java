import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class StartServer {

    private static final String HOST = "localhost";
    public static final int PORT = 9990;
    public static WireMockServer server = new WireMockServer(PORT);

    @BeforeAll
    public static void initialize() {
        System.out.println("init");

        // запуск сервера
        server.start();
        WireMock.configureFor(HOST, PORT);

        // конфигурация заглушки для ответа
        ResponseDefinitionBuilder mockRes = new ResponseDefinitionBuilder();
        mockRes.withStatus(201);
        mockRes.withStatusMessage("hello guys");
        mockRes.withHeader("Content-Type", "text/json");
        mockRes.withHeader("token", "12345");
        mockRes.withHeader("Set-Cookie", "session-id=91811");
        mockRes.withBody("text to body");

        // конфигурирую эндпоинт и что надо вернуть в ответе
        WireMock.givenThat(WireMock.get("/emps/1").willReturn(mockRes));

        ResponseDefinitionBuilder mockResFile = new ResponseDefinitionBuilder();
        mockResFile.withStatus(201);
        mockResFile.withBodyFile("index.json");
        mockResFile.withStatusMessage("hello guys");
        mockResFile.withHeader("Content-Type", "text/json");
        mockResFile.withHeader("token", "12345");
        mockResFile.withHeader("Set-Cookie", "session-id=91811");

        // конфигурирую эндпоинт и что надо вернуть в ответе
        WireMock.givenThat(WireMock.get("/emps/2").willReturn(mockResFile));

    }

    @AfterAll
    static void close() {
        server.shutdown();
    }
}
