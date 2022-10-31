import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Surveys {
    // 설문시작
    public void Survey(Statement statement) {
        System.out.println("설문시작");
        // 이름과 휴대폰 입력 확인
        System.out.print("이름 : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.print("휴대폰 번호 : ");
        String phone_number = scanner.next();

        Commons commons = new Commons();
        String strDate = commons.getGeneratorID();

        String query = "INSERT INTO users_list (USERS_UID, PHONE, NAME)" +
                "VALUES ('" + strDate + "', '" + phone_number + "', '" + name + "');";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 설문과 답항 내용 출력
        query = "SELECT * FROM questions_list ORDER BY ORDERS;";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("ORDERS") + ". ");
                System.out.println(resultSet.getString("QUESTIONS"));
                String uid = resultSet.getString("QUESTIONS_UID");
                query = "SLECT example_list.EXAMPE_UID, example_list.EXAMPLE, example_list.ORDERS " +
                        "FROM answers inner Join example_list " +
                        "on answers.EXAMPLE_UID = example_list.EXAMPLE_UID " +
                        "WHERE QUESTIONS_UID = '" + uid + "' " +
                        "ORDER BY ORDERS";
                ResultSet resultSet2 = statement.executeQuery(query);
                ArrayList<String> survey_list = new ArrayList<>();
                //설문 답항 출력
                while(resultSet2.next()){
                    System.out.print(resultSet2.getInt("ORDERS") + ". ");
                    System.out.println(resultSet2.getString("EXAMPLE"));
                    example_list.add(resultSet2.getString("EXAMPLE_UID"));
                }
                //설문자 답 받기
                System.out.println("응답 번호 : ");
                Integer answer = scanner.nextInt();

                // strDate; //설문자 UID
                // uid; //설문 문항 UID
                // example_list.get(answer); //설문자 답변 UID
                query = "INSERT INTO SURVEY (USERS_UID, QUESTIONS_UID, EXAMPLE_UID) " +
                        "VALUES ('" + strDate + "', '" + uid + "', ' " + example_list.get(answer) +"')";
                statement.executeQuery(query);


                //설문 문항에 맞는 설문 답항 출력
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 설문자 답 받기

    }
}
