package com.example.project;

import com.example.project.activities.ViewPollsActivityStudent;
import com.example.project.models.Poll;
import com.example.project.models.Question;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ViewPollsActivityTests {


    @Test
    public void PollIsOpen(){
        String startDate = "01-01-2018";
        String endDate = "02-02-2020";
        assertTrue(ViewPollsActivityStudent.checkDatesIfOpen(startDate, endDate));

    }
}
