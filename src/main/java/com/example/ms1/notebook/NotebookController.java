package com.example.ms1.notebook;


import com.example.ms1.note.note.Note;
import com.example.ms1.note.note.NoteRepository;
import com.example.ms1.note.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NotebookController {

    private final NotebookRepository notebookRepository;
    private final NoteService noteService;


    @PostMapping("/books/write")
    public String write(){
        Notebook notebook = new Notebook();
        notebook.setName("새노트북");
        notebookRepository.save(notebook);
        noteService.saveDefault(notebook);
        return "redirect:/";


    }

    @GetMapping("/books/{notebookId}/detail")
    public String detail(@PathVariable("notebookId") Long notebookId){
        Notebook targetNotebook = notebookRepository.findById(notebookId).orElseThrow();
        Note note = targetNotebook. getNoteList().get(0);

        return "redirect:/books/%d/notes/%d".formatted(notebookId,note.getId());
    }

}
