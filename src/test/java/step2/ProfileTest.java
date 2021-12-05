package step2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @BeforeEach
    void create() {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "Got Bonuses?");
        criteria = new Criteria();

    }

    @Test
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // Given
        profile.add(new Answer(question, Bool.FALSE));

        Criterion criterion = new Criterion(Weight.MustMatch, new Answer(question, Bool.TRUE));
        criteria.add(criterion);

        // When
        boolean matches = profile.matches(criteria);

        // Then
        assertFalse(matches);
    }

    @Test
    void matchAnswersFalseWhenDontCareCriteriaMet() {
        // Given
        profile.add(new Answer(question, Bool.FALSE));

        Criterion criterion = new Criterion(Weight.DontCare, new Answer(question, Bool.TRUE));
        criteria.add(criterion);
        // When

        boolean matches = profile.matches(criteria);
        // Then

        assertTrue(matches);
    }

    @Test
    void matchAnswersTrueWhenMustMatchCriteriaMet() {
        // Given
        profile.add(new Answer(question, Bool.TRUE));

        Criterion criterion = new Criterion(Weight.DontCare, new Answer(question, Bool.TRUE));
        criteria.add(criterion);
        // When
        boolean matches = profile.matches(criteria);

        // Then
        assertTrue(matches);
    }

    @Test
    void matchAnswersTrueWhenDontCareCriteriaMet() {
        // Given
        profile.add(new Answer(question, Bool.TRUE));

        Criterion criterion = new Criterion(Weight.DontCare, new Answer(question, Bool.TRUE));
        criteria.add(criterion);
        // When
        boolean matches = profile.matches(criteria);

        // Then
        assertTrue(matches);
    }

    @Test
    void matchAnswersTrueWhenWouldPreferCriteriaMet() {
        // Given
        profile.add(new Answer(question, Bool.TRUE));

        Criterion criterion = new Criterion(Weight.WouldPrefer, new Answer(question, Bool.TRUE));
        criteria.add(criterion);
        // When
        boolean matches = profile.matches(criteria);

        // Then
        assertTrue(matches);
    }


    @Test
    void matchAnswersFalseWhenWouldPreferCriteriaMet() {
        // Given
        profile.add(new Answer(question, Bool.FALSE));

        Criterion criterion = new Criterion(Weight.WouldPrefer, new Answer(question, Bool.TRUE));
        criteria.add(criterion);
        // When
        boolean matches = profile.matches(criteria);

        // Then
        assertFalse(matches);
    }
}