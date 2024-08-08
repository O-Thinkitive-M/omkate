package com.example.serviceImpl;
import com.example.entity.Account;
import com.example.entity.Library;
import com.example.entity.Student;
import com.example.repo.StudentRepo;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

     private static final String LIBRARY_SERVICE="http://localhost:7777/library";
     private static final String ACCOUNT_SERVICE="http://localhost:9899/account";

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private WebClient webClient;

    @Override
    public String createStudent(Student student) {

        Mono<Long> response=webClient.post().uri(LIBRARY_SERVICE+"/create")
                .body(Mono.just(student.getLibrary()), Library.class)
                .retrieve()
                .bodyToMono(Long.class);
       response.subscribe(value ->{
           if(value>0)
           {
               System.out.println(value);
               student.setLibraryid(value);
               studentRepo.save(student);
           }
       });
        return "Done..!";
    }
    @Override
    public Student getSingleStudent(Long id) {
        Student s=studentRepo.findById(id).get();
        Library li= getLibrary(s.getLibraryid());
            s.setLibrary(li);
        return s;
    }
    @Override
    public List<Student> getAllStudent() {
        List<Student> st=studentRepo.findAll();
         List<Student> s=st.stream().map(student -> {
             Library library=getLibrary(student.getLibraryid());
              student.setLibrary(library);
              return student;
         }).toList();
         return s;
    }
    public Library getLibrary(Long id) {
        return webClient.get().uri(LIBRARY_SERVICE+"/get-library/{id}",id)
                .retrieve()
                .bodyToMono(Library.class)
                .block();
    }
//    get Accounts form Accounts Service
    public List<Account> getAccounts()
    {
       return webClient.get().uri(ACCOUNT_SERVICE+"/getaccounts")
                .retrieve()
                .bodyToFlux(Account.class)
                .collectList().block();
    }
}