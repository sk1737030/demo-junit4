package step3;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatCompiler {
    private QuestionController controller = new QuestionController();

    public Map<String, Map<Boolean, AtomicInteger>> responseByQuestion(List<BooleanAnswer> answers) {
        Map<String, Map<Boolean, AtomicInteger>> responses = new HashMap<>();
        answers.stream().forEach(answer -> incrementHistogram(responses,answers));
        return convertHistogramIdsToText(responses);
    }

    private void incrementHistogram(Map<String, Map<Boolean, AtomicInteger>> responses, Object answer) {

    }

    private Map<String, Map<Boolean, AtomicInteger>> convertHistogramIdsToText(Map<String, Map<Boolean, AtomicInteger>> responses) {
        Map<String, Map<Boolean, AtomicInteger>> textResponses = new HashMap<>();
        responses.keySet().stream().forEach(id -> textResponses.put(controller.find(id).getText(), responses.get(id)));
        return textResponses;
    }

    private Map<Boolean, AtomicInteger> getHistogram(Map<Integer, Map<Boolean, AtomicInteger>> responses, int id ) {
        Map<Boolean, AtomicInteger> histogram = null;
        if(responses.containsKey(id))
            histogram = responses.get(id);
        else {
            histogram = createNewHistogram();
            responses.put(id, histogram);
        }

        return histogram;
    }

    private Map<Boolean, AtomicInteger> createNewHistogram() {
        Map<Boolean, AtomicInteger> histogram;
        histogram = new HashMap<>();
        histogram.put(Boolean.FALSE, new AtomicInteger(0));
        histogram.put(Boolean.TRUE, new AtomicInteger(0));
        return histogram;
    }

}
