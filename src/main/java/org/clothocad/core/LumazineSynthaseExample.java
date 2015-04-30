package org.clothocad.core;

import org.clothocad.core.ClothoBuilder;
import org.clothocad.core.persistence.jongo.JongoModule;
import org.clothocad.core.persistence.Persistor;
import org.clothocad.core.security.nosecurity.NoSecurityModule;
import org.clothocad.model.BasicModule;
import org.clothocad.model.BioDesign;
import org.clothocad.model.CompositeModule;
import org.clothocad.model.Feature;
import org.clothocad.model.Feature.FeatureRole;
import org.clothocad.model.Influence;
import org.clothocad.model.Influence.InfluenceType;
import org.clothocad.model.Module;
import org.clothocad.model.Module.ModuleRole;
import org.clothocad.model.Part;
import org.clothocad.model.Person;
import org.clothocad.model.Sequence;
import org.clothocad.model.Strain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* This example is based off of an entry from the iGEM registry
* http://parts.igem.org/Part:BBa_K542008
*/
public class LumazineSynthaseExample {
    public static void main(String[] args) {
        ClothoBuilder builder = new ClothoBuilder(new NoSecurityModule(), new JongoModule());
        Persistor p = builder.get(Persistor.class);

        BioDesign bd = createBBaK542008();
        p.save(bd);
    }

    public static BioDesign createBBaK542008() {
        Person nic = new Person("Nicholas Roehner");
        Person unknown = new Person("Unknown");
        Person anthony = new Person("Anthony Vuong");
        Person vinay = new Person("Vinay S. Mahajan");
        Person roxanne = new Person("Roxanne Shank");
        Person randy = new Person("Randy Rettberg");
        Person reshma = new Person("Reshma Shetty");
        Person jkm = new Person("J. K. M.");
        Person june = new Person("June Rhee");
        Person lisza = new Person("Lisza Bruder");
        Person kristen = new Person("Kristen DeCelle");
        Person adam = new Person("Adam Smith");
        BioDesign desBBaK542008 = new BioDesign("BBa_K542008", anthony);
        
        Sequence seqK542008 = new Sequence("seq", "caatacgcaaaccgcctctccccgcgcgttggccgattcattaatgcagctggcacgacaggtttcccgactggaaagcgggcagtgagcgcaacgcaattaatgtgagttagctcactcattaggcaccccaggctttacactttatgcttccggctcgtatgttgtgtggaattgtgagcggataacaatttcacacatactagagaaagaggagaaatactagatgcagatttatgaaggcaaactgaccgcggaaggcctgcgctttggcattgtggcgagccgctttaaccatgcgctggtggatcgcctggtggaaggcgcgattgattgcattgtgcgccatggtggtcgcgaagaagatattaccctggtgcgcgtgccgggcagctgggaaattccggtggcggcgggcgaactggcgcgcaaagaagatattgatgcggtgattgcgattggcgtgctgattgaaggcgcggaaccgcattttgattatattgcgagcgaagtgagcaaaggcctggcgaacctgagcctggaactgcgcaaaccgattacctttggcgtgattaccgcggatgaactggaagaagcgattgaacgcgcgggcaccaaacatggcaacaaaggctgggaagcggcgctgagcgcgattgaaatggcgaacctgtttaaaagcctgcgctagtactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttatatactagagacattgattatttgcacggcgtcacactttgctatgccatagcatttttatccataagattagcggatcctacctgacgctttttatcgcaactctctactgtttctccataccgtttttttgggctagctactagagaaagaggagaaatactagatgtccagattagataaaagtaaagtgattaacagcgcattagagctgcttaatgaggtcggaatcgaaggtttaacaacccgtaaactcgcccagaagctaggtgtagagcagcctacattgtattggcatgtaaaaaataagcgggctttgctcgacgccttagccattgagatgttagataggcaccatactcacttttgccctttagaaggggaaagctggcaagattttttacgtaataacgctaaaagttttagatgtgctttactaagtcatcgcgatggagcaaaagtacatttaggtacacggcctacagaaaaacagtatgaaactctcgaaaatcaattagcctttttatgccaacaaggtttttcactagagaatgcattatatgcactcagcgctgtggggcattttactttaggttgcgtattggaagatcaagagcatcaagtcgctaaagaagaaagggaaacacctactactgatagtatgccgccattattacgacaagctatcgaattatttgatcaccaaggtgcagagccagccttcttattcggccttgaattgatcatatgcggattagaaaaacaacttaaatgtgaaagtgggtccgctgcaaacgacgaaaactacgctttagtagcttaataacactgatagtgctagtgtagatcactactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttatatactagagtccctatcagtgatagagattgacatccctatcagtgatagagatactgagcactactagagaaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtgaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccctgacctggggcgtgcagtgcttcagccgctaccccgaccacatgaagcagcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacatcagccacaacgtctatatcaccgccgacaagcagaagaacggcatcaaggccaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagcacccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaatactagagaaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtaaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccttcggctacggcctgcaatgcttcgcccgctaccccgaccacatgaagctgcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacaacagccacaacgtctatatcatggccgacaagcagaagaacggcatcaaggtgaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagctaccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaatactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", anthony);
        
        Sequence seqK542004 = new Sequence("seq", "caatacgcaaaccgcctctccccgcgcgttggccgattcattaatgcagctggcacgacaggtttcccgactggaaagcgggcagtgagcgcaacgcaattaatgtgagttagctcactcattaggcaccccaggctttacactttatgcttccggctcgtatgttgtgtggaattgtgagcggataacaatttcacacatactagagaaagaggagaaatactagatgcagatttatgaaggcaaactgaccgcggaaggcctgcgctttggcattgtggcgagccgctttaaccatgcgctggtggatcgcctggtggaaggcgcgattgattgcattgtgcgccatggtggtcgcgaagaagatattaccctggtgcgcgtgccgggcagctgggaaattccggtggcggcgggcgaactggcgcgcaaagaagatattgatgcggtgattgcgattggcgtgctgattgaaggcgcggaaccgcattttgattatattgcgagcgaagtgagcaaaggcctggcgaacctgagcctggaactgcgcaaaccgattacctttggcgtgattaccgcggatgaactggaagaagcgattgaacgcgcgggcaccaaacatggcaacaaaggctgggaagcggcgctgagcgcgattgaaatggcgaacctgtttaaaagcctgcgctagtactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttatatactagagacattgattatttgcacggcgtcacactttgctatgccatagcatttttatccataagattagcggatcctacctgacgctttttatcgcaactctctactgtttctccataccgtttttttgggctagctactagagaaagaggagaaatactagatgtccagattagataaaagtaaagtgattaacagcgcattagagctgcttaatgaggtcggaatcgaaggtttaacaacccgtaaactcgcccagaagctaggtgtagagcagcctacattgtattggcatgtaaaaaataagcgggctttgctcgacgccttagccattgagatgttagataggcaccatactcacttttgccctttagaaggggaaagctggcaagattttttacgtaataacgctaaaagttttagatgtgctttactaagtcatcgcgatggagcaaaagtacatttaggtacacggcctacagaaaaacagtatgaaactctcgaaaatcaattagcctttttatgccaacaaggtttttcactagagaatgcattatatgcactcagcgctgtggggcattttactttaggttgcgtattggaagatcaagagcatcaagtcgctaaagaagaaagggaaacacctactactgatagtatgccgccattattacgacaagctatcgaattatttgatcaccaaggtgcagagccagccttcttattcggccttgaattgatcatatgcggattagaaaaacaacttaaatgtgaaagtgggtccgctgcaaacgacgaaaactacgctttagtagcttaataacactgatagtgctagtgtagatcactactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", anthony);
        Sequence seqK542001 = new Sequence("seq", "caatacgcaaaccgcctctccccgcgcgttggccgattcattaatgcagctggcacgacaggtttcccgactggaaagcgggcagtgagcgcaacgcaattaatgtgagttagctcactcattaggcaccccaggctttacactttatgcttccggctcgtatgttgtgtggaattgtgagcggataacaatttcacacatactagagaaagaggagaaatactagatgcagatttatgaaggcaaactgaccgcggaaggcctgcgctttggcattgtggcgagccgctttaaccatgcgctggtggatcgcctggtggaaggcgcgattgattgcattgtgcgccatggtggtcgcgaagaagatattaccctggtgcgcgtgccgggcagctgggaaattccggtggcggcgggcgaactggcgcgcaaagaagatattgatgcggtgattgcgattggcgtgctgattgaaggcgcggaaccgcattttgattatattgcgagcgaagtgagcaaaggcctggcgaacctgagcctggaactgcgcaaaccgattacctttggcgtgattaccgcggatgaactggaagaagcgattgaacgcgcgggcaccaaacatggcaacaaaggctgggaagcggcgctgagcgcgattgaaatggcgaacctgtttaaaagcctgcgctagtactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", anthony);
        Sequence seqJ04500 = new Sequence("seq", "caatacgcaaaccgcctctccccgcgcgttggccgattcattaatgcagctggcacgacaggtttcccgactggaaagcgggcagtgagcgcaacgcaattaatgtgagttagctcactcattaggcaccccaggctttacactttatgcttccggctcgtatgttgtgtggaattgtgagcggataacaatttcacacatactagagaaagaggagaaa", kristen);
        Sequence seqK542000 = new Sequence("seq", "atgcagatttatgaaggcaaactgaccgcggaaggcctgcgctttggcattgtggcgagccgctttaaccatgcgctggtggatcgcctggtggaaggcgcgattgattgcattgtgcgccatggtggtcgcgaagaagatattaccctggtgcgcgtgccgggcagctgggaaattccggtggcggcgggcgaactggcgcgcaaagaagatattgatgcggtgattgcgattggcgtgctgattgaaggcgcggaaccgcattttgattatattgcgagcgaagtgagcaaaggcctggcgaacctgagcctggaactgcgcaaaccgattacctttggcgtgattaccgcggatgaactggaagaagcgattgaacgcgcgggcaccaaacatggcaacaaaggctgggaagcggcgctgagcgcgattgaaatggcgaacctgtttaaaagcctgcgctagtactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", anthony);
        Sequence seqB0015 = new Sequence("seq", "ccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", reshma);
        Sequence seqK542003 = new Sequence("seq", "acattgattatttgcacggcgtcacactttgctatgccatagcatttttatccataagattagcggatcctacctgacgctttttatcgcaactctctactgtttctccataccgtttttttgggctagctactagagaaagaggagaaatactagatgtccagattagataaaagtaaagtgattaacagcgcattagagctgcttaatgaggtcggaatcgaaggtttaacaacccgtaaactcgcccagaagctaggtgtagagcagcctacattgtattggcatgtaaaaaataagcgggctttgctcgacgccttagccattgagatgttagataggcaccatactcacttttgccctttagaaggggaaagctggcaagattttttacgtaataacgctaaaagttttagatgtgctttactaagtcatcgcgatggagcaaaagtacatttaggtacacggcctacagaaaaacagtatgaaactctcgaaaatcaattagcctttttatgccaacaaggtttttcactagagaatgcattatatgcactcagcgctgtggggcattttactttaggttgcgtattggaagatcaagagcatcaagtcgctaaagaagaaagggaaacacctactactgatagtatgccgccattattacgacaagctatcgaattatttgatcaccaaggtgcagagccagccttcttattcggccttgaattgatcatatgcggattagaaaaacaacttaaatgtgaaagtgggtccgctgcaaacgacgaaaactacgctttagtagcttaataacactgatagtgctagtgtagatcactactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", anthony);
        Sequence seqP0440 = new Sequence("seq", "aaagaggagaaatactagatgtccagattagataaaagtaaagtgattaacagcgcattagagctgcttaatgaggtcggaatcgaaggtttaacaacccgtaaactcgcccagaagctaggtgtagagcagcctacattgtattggcatgtaaaaaataagcgggctttgctcgacgccttagccattgagatgttagataggcaccatactcacttttgccctttagaaggggaaagctggcaagattttttacgtaataacgctaaaagttttagatgtgctttactaagtcatcgcgatggagcaaaagtacatttaggtacacggcctacagaaaaacagtatgaaactctcgaaaatcaattagcctttttatgccaacaaggtttttcactagagaatgcattatatgcactcagcgctgtggggcattttactttaggttgcgtattggaagatcaagagcatcaagtcgctaaagaagaaagggaaacacctactactgatagtatgccgccattattacgacaagctatcgaattatttgatcaccaaggtgcagagccagccttcttattcggccttgaattgatcatatgcggattagaaaaacaacttaaatgtgaaagtgggtccgctgcaaacgacgaaaactacgctttagtagcttaataacactgatagtgctagtgtagatcactactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", randy);
        
        Sequence seqK542005 = new Sequence("seq", "tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcactactagagaaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtgaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccctgacctggggcgtgcagtgcttcagccgctaccccgaccacatgaagcagcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacatcagccacaacgtctatatcaccgccgacaagcagaagaacggcatcaaggccaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagcacccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaatactagagaaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtaaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccttcggctacggcctgcaatgcttcgcccgctaccccgaccacatgaagctgcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacaacagccacaacgtctatatcatggccgacaagcagaagaacggcatcaaggtgaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagctaccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaatactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", anthony);
        Sequence seqK331033 = new Sequence("seq", "tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcactactagagaaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtgaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccctgacctggggcgtgcagtgcttcagccgctaccccgaccacatgaagcagcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacatcagccacaacgtctatatcaccgccgacaagcagaagaacggcatcaaggccaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagcacccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaa", adam);
        Sequence seqK331025 = new Sequence("seq", "aaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtgaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccctgacctggggcgtgcagtgcttcagccgctaccccgaccacatgaagcagcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacatcagccacaacgtctatatcaccgccgacaagcagaagaacggcatcaaggccaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagcacccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaa", adam);
        Sequence seqK331035 = new Sequence("seq", "aaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtaaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccttcggctacggcctgcaatgcttcgcccgctaccccgaccacatgaagctgcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacaacagccacaacgtctatatcatggccgacaagcagaagaacggcatcaaggtgaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagctaccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaatactagagccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctctactagagtcacactggctcaccttcgggtgggcctttctgcgtttata", adam);
        Sequence seqK331023 = new Sequence("seq", "aaagaggagaaatactagatggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtaaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccttcggctacggcctgcaatgcttcgcccgctaccccgaccacatgaagctgcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacaacagccacaacgtctatatcatggccgacaagcagaagaacggcatcaaggtgaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagctaccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaa", adam);
        
        Sequence seqR0010 = new Sequence("seq", "caatacgcaaaccgcctctccccgcgcgttggccgattcattaatgcagctggcacgacaggtttcccgactggaaagcgggcagtgagcgcaacgcaattaatgtgagttagctcactcattaggcaccccaggctttacactttatgcttccggctcgtatgttgtgtggaattgtgagcggataacaatttcacaca", unknown);
        Sequence seqB0034 = new Sequence("seq", "aaagaggagaaa", vinay);
        Sequence seqK249002 = new Sequence("seq", "atgcagatttatgaaggcaaactgaccgcggaaggcctgcgctttggcattgtggcgagccgctttaaccatgcgctggtggatcgcctggtggaaggcgcgattgattgcattgtgcgccatggtggtcgcgaagaagatattaccctggtgcgcgtgccgggcagctgggaaattccggtggcggcgggcgaactggcgcgcaaagaagatattgatgcggtgattgcgattggcgtgctgattgaaggcgcggaaccgcattttgattatattgcgagcgaagtgagcaaaggcctggcgaacctgagcctggaactgcgcaaaccgattacctttggcgtgattaccgcggatgaactggaagaagcgattgaacgcgcgggcaccaaacatggcaacaaaggctgggaagcggcgctgagcgcgattgaaatggcgaacctgtttaaaagcctgcgctag", roxanne);
        Sequence seqB0010 = new Sequence("seq", "ccaggcatcaaataaaacgaaaggctcagtcgaaagactgggcctttcgttttatctgttgtttgtcggtgaacgctctc", randy);
        Sequence seqB0012 = new Sequence("seq", "tcacactggctcaccttcgggtgggcctttctgcgtttata", reshma);
        Sequence seqI13453 = new Sequence("seq", "acattgattatttgcacggcgtcacactttgctatgccatagcatttttatccataagattagcggatcctacctgacgctttttatcgcaactctctactgtttctccataccgtttttttgggctagc", jkm);
        Sequence seqC0040 = new Sequence("seq", "atgtccagattagataaaagtaaagtgattaacagcgcattagagctgcttaatgaggtcggaatcgaaggtttaacaacccgtaaactcgcccagaagctaggtgtagagcagcctacattgtattggcatgtaaaaaataagcgggctttgctcgacgccttagccattgagatgttagataggcaccatactcacttttgccctttagaaggggaaagctggcaagattttttacgtaataacgctaaaagttttagatgtgctttactaagtcatcgcgatggagcaaaagtacatttaggtacacggcctacagaaaaacagtatgaaactctcgaaaatcaattagcctttttatgccaacaaggtttttcactagagaatgcattatatgcactcagcgctgtggggcattttactttaggttgcgtattggaagatcaagagcatcaagtcgctaaagaagaaagggaaacacctactactgatagtatgccgccattattacgacaagctatcgaattatttgatcaccaaggtgcagagccagccttcttattcggccttgaattgatcatatgcggattagaaaaacaacttaaatgtgaaagtgggtccgctgcaaacgacgaaaactacgctttagtagcttaataacactgatagtgctagtgtagatcac", june);
        Sequence seqR0040 = new Sequence("seq", "tccctatcagtgatagagattgacatccctatcagtgatagagatactgagcac", june);
        Sequence seqK331002 = new Sequence("seq", "atggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtgaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccctgacctggggcgtgcagtgcttcagccgctaccccgaccacatgaagcagcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacatcagccacaacgtctatatcaccgccgacaagcagaagaacggcatcaaggccaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagcacccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaa", lisza);
        Sequence seqK249006 = new Sequence("seq", "atggtgagcaagggcgaggagctgttcaccggggtggtgcccatcctggtcgagctggacggcgacgtaaacggccacaagttcagcgtgtccggcgagggcgagggcgatgccacctacggcaagctgaccctgaagttcatctgcaccaccggcaagctgcccgtgccctggcccaccctcgtgaccaccttcggctacggcctgcaatgcttcgcccgctaccccgaccacatgaagctgcacgacttcttcaagtccgccatgcccgaaggctacgtccaggagcgcaccatcttcttcaaggacgacggcaactacaagacccgcgccgaggtgaagttcgagggcgacaccctggtgaaccgcatcgagctgaagggcatcgacttcaaggaggacggcaacatcctggggcacaagctggagtacaactacaacagccacaacgtctatatcatggccgacaagcagaagaacggcatcaaggtgaacttcaagatccgccacaacatcgaggacggcagcgtgcagctcgccgaccactaccagcagaacacccccatcggcgacggccccgtgctgctgcccgacaaccactacctgagctaccagtccgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagcactagagccgccgccgccgccgccgccgccgccgctaa", roxanne);
        
        Feature featK542001 = new Feature("pLacI Regulated Lumazine Synthase", FeatureRole.GENE, anthony);
        featK542001.setSequence(seqK542001);
        Feature featK542003 = new Feature("pBAD Regulated TetR", FeatureRole.GENE, anthony);
        featK542003.setSequence(seqK542003);
        
        Feature featK542005 = new Feature("pTet Regulated Arg-tagged ECFP and EYFP (FRET Reporter)", FeatureRole.GENE, anthony);
        featK542005.setSequence(seqK542005);
        
        Feature featB0015 = new Feature("double terminator (B0010-B0012)", FeatureRole.TERMINATOR, reshma);
        featB0015.setDescription("Double terminator consisting of BBa_B0010 and BBa_B0012. This is the most commonly used terminator. It seems to be reliable. Note, however, that Part:BBa_B0014 is a better part for forward and reverse termination");
        featB0015.setSequence(seqB0015);
        
        Feature featR0010 = new Feature("promoter (lacI regulated)", FeatureRole.PROMOTER, unknown);
        featR0010.setDescription("This part is an inverting regulator sensitive to LacI and CAP. It contains two protein binding sites. The first binds the CAP protein, which is generally present in E.coli and is asocciated with cell health and availability of glucose. The second binds LacI protein. In the absence of LacI protein and CAP protein, this part promotes transcription. In the presence of LacI protein and CAP protein, this part inhibits transcription. LacI can be inhibited by IPTG. LacI is coded by BBa_C0010 ");
        featR0010.setSequence(seqR0010);
        Feature featB0034 = new Feature("RBS (Elowitz 1999) -- defines RBS efficiency", FeatureRole.RBS, vinay);
        featB0034.setDescription("RBS based on Elowitz repressilator.");
        featB0034.setSequence(seqB0034);
        Feature featK249002 = new Feature("Lumazine Synthase", FeatureRole.CDS, roxanne);
        featK249002.setDescription("Lumazine Synthase is an enzyme which creates Lumazine, a product which aggregates forming a hollow spheroid which can act as a mirocompartment, or artificial organelle. The Lumazine forms negatively charged pores, which can be used to introduce proteins. The proteins which are being introduced into the microcompartment must be equipped with an Arginine Tag. Seebeck, F. P., Woycechowsky, K. J., Zhuang, W., Rabe, J. P., and Hilvert, D., (2006). A simple tagging system for protein encapulation. J. Am. Chem. Soc. 128, 4516-4517.");
        featK249002.setSequence(seqK249002);
        Feature featB0010 = new Feature("T1 from E. coli rrnB", FeatureRole.TERMINATOR, randy);
        featB0010.setDescription("Transcriptional terminator consisting of a 64 bp stem-loop.");
        featB0010.setSequence(seqB0010);
        Feature featB0012 = new Feature("TE from coliphageT7", FeatureRole.TERMINATOR, reshma);
        featB0012.setDescription("Transcription terminator for the E.coli RNA polymerase. (This is a bad terminator, it is a promoter in the reverse direction.)");
        featB0012.setSequence(seqB0012);
        Feature featI13453 = new Feature("Pbad promoter", FeatureRole.PROMOTER, jkm);
        featI13453.setDescription("PBad promoter from I0500 without AraC.");
        featI13453.setSequence(seqI13453);
        Feature featC0040 = new Feature("tetracycline repressor from transposon Tn10 (+LVA)", FeatureRole.CDS, june);
        featC0040.setDescription("Coding region for the TetR protein without the Ribosome Binding Site. Modified with an LVA tail for rapid degradation of the protein and faster fall time for the emission. TetR binds to the pTet regulator (Part:BBa_R0040). aTc (anhydrotetracycline) binds to TetR and inhibits its operation.");
        featC0040.setSequence(seqC0040);
        Feature featR0040 = new Feature("TetR repressible promoter", FeatureRole.PROMOTER, june);
        featR0040.setDescription("Sequence for pTet inverting regulator. Promoter is constitutively ON and repressed by TetR. TetR repression is inhibited by the addition of tetracycline or its analog, aTc.");
        featR0040.setSequence(seqR0040);
        Feature featK331002 = new Feature("ECFP with C-terminal Arginine Tag", FeatureRole.CDS, lisza);
        featK331002.setDescription("This protein is the ECFP fused with the C-terminal Arginine tag found in BBa_K249005. It is designed to be part of a construct for targeting into the lumazine synthase microcompatment (BBa_K331000).");
        featK331002.setSequence(seqK331002);
        Feature featK249006 = new Feature("Fusion EYFP with C-terminal Arginine Tag", FeatureRole.CDS, roxanne);
        featK249006.setDescription("This Yellow Fluorescent protein has been fused with 10 C-terminal Arginines to target it into the Lumazine-based microcompartment.");
        featK249006.setSequence(seqK249006);
        
        Module modI13453 = new BasicModule("Transcription via pBad", ModuleRole.TRANSCRIPTION, Arrays.asList(featI13453, featB0015), nic);
        Module modB0034 = new BasicModule("Translation via Elowitz RBS", ModuleRole.TRANSLATION, Arrays.asList(featB0034), nic);
        Module modC0040 = new BasicModule("TetR Expression", ModuleRole.EXPRESSION, Arrays.asList(featB0034, featC0040), nic);
        Module modK542003 = new CompositeModule("TetR EXPRESSION", ModuleRole.EXPRESSION, Arrays.asList(modI13453, modB0034, modC0040), nic);
        
        Module modR0040 = new BasicModule("Transcription via pTet", ModuleRole.TRANSCRIPTION, Arrays.asList(featR0040, featB0015), nic);
        Module modK331002 = new BasicModule("ECFP Expression", ModuleRole.EXPRESSION, Arrays.asList(featB0034, featK331002), nic);
        Module modK249006 = new BasicModule("EYFP Expression", ModuleRole.EXPRESSION, Arrays.asList(featB0034, featK249006), nic);
        Module modK542005 = new CompositeModule("ECFP and EYFP Expression", ModuleRole.EXPRESSION, Arrays.asList(modR0040, modB0034, modK331002, modK249006), nic);
        Module modFRET = new CompositeModule("FRET Reporter", ModuleRole.REPORTER, Arrays.asList(modK542005), nic);
        
        Module modR0010 = new BasicModule("Transcription via pLac", ModuleRole.TRANSCRIPTION, Arrays.asList(featR0010, featB0015), nic);
        Module modK249002 = new BasicModule("Lumazine Synthase Expression", ModuleRole.EXPRESSION, Arrays.asList(featK249002), nic);
        Module modK542001 = new CompositeModule("Lumazine Synthase Expression", ModuleRole.EXPRESSION, Arrays.asList(modR0010, modB0034, modK249002), nic);
        Module modLuma = new CompositeModule("Lumazine Microcompartment Generator", ModuleRole.COMPARTMENTALIZATION, Arrays.asList(modK542001), nic);
        
        Influence inflC0040_R0040 = new Influence("Repression via TetR", featC0040, featR0040, InfluenceType.REPRESSION, nic);
        
        Module modK542008 = new CompositeModule("ECFP and EYFP Localization", ModuleRole.LOCALIZATION, Arrays.asList(modK542003, modLuma, modFRET), nic);
        Set<Influence> tempInfls = new HashSet<Influence>();
        tempInfls.add(inflC0040_R0040);
        modK542008.setInfluences(tempInfls);
        
        Part partB0010 = new Part("BBa_B0010", seqB0010, randy);
        Part partB0012 = new Part("BBa_B0012", seqB0012, reshma);
        Part partR0010 = new Part("BBa_R0010", seqR0010, unknown);
        Part partB0034 = new Part("BBa_B0034", seqB0034, vinay);
        Part partK249002 = new Part("BBa_K249002", seqK249002, roxanne);
        Part partI13453 = new Part("BBa_I13453", seqI13453, jkm);
        Part partR0040 = new Part("BBa_R0040", seqR0040, june);
        Part partK331002 = new Part("BBa_K331002", seqK331002, lisza);
        Part partK249006 = new Part("BBa_K249006", seqK249006, roxanne);
        
        Part partK331025 = new Part("BBa_K331025", seqK331025, adam);
        partK331025.createAssembly().setParts(Arrays.asList(partB0034, partK331002));
        Part partK331023 = new Part("BBa_K331023", seqK331023, adam);
        partK331023.createAssembly().setParts(Arrays.asList(partB0034, partK249006));
        Part partB0015 = new Part("BBa_B0015", seqB0015, reshma);
        partB0015.createAssembly().setParts(Arrays.asList(partB0010, partB0012));
        
        Part partK331033 = new Part("BBa_K331033", seqK331033, adam);
        partK331033.createAssembly().setParts(Arrays.asList(partR0040, partK331025));
        Part partK331035 = new Part("BBa_K331035", seqK331035, adam);
        partK331035.createAssembly().setParts(Arrays.asList(partK331023, partB0015));
        
        Part partK542005 = new Part("BBa_K542005", seqK542005, anthony);
        partK542005.createAssembly().setParts(Arrays.asList(partK331033, partK331035));
        
        Part partP0440 = new Part("BBa_P0440", seqP0440, randy);
        Part partJ04500 = new Part("BBa_J04500", seqJ04500, kristen);
        partJ04500.createAssembly().setParts(Arrays.asList(partR0010, partB0034));
        Part partK542000 = new Part("BBa_K542000", seqK542000, anthony);
        partK542000.createAssembly().setParts(Arrays.asList(partK249002, partB0015));
        
        Part partK542001 = new Part("BBa_K542001", seqK542001, anthony);
        partK542001.createAssembly().setParts(Arrays.asList(partJ04500, partK542000));
        Part partK542003 = new Part("BBa_K542003", seqK542003, anthony);
        partK542003.createAssembly().setParts(Arrays.asList(partI13453, partP0440));
        
        Part partK542004 = new Part("BBa_K542004", seqK542004, anthony);
        partK542004.createAssembly().setParts(Arrays.asList(partK542001, partK542003));
        
        Part partK542008 = new Part("BBa_K542008", seqK542008, anthony);
        partK542008.createAssembly().setParts(Arrays.asList(partK542004, partK542005));
        
        Strain strainEcoliDH5a = new Strain("E. coli DH5alpha", nic);
        
        desBBaK542008.setModule(modK542008);
        Set<Part> tempParts = new HashSet<Part>();
        tempParts.add(partK542008);
        desBBaK542008.setParts(tempParts);
        Set<Strain> tempStrains = new HashSet<Strain>();
        tempStrains.add(strainEcoliDH5a);
        desBBaK542008.setStrains(tempStrains);
        
        return desBBaK542008;
    }
}
