import { createGlobalStyle } from 'styled-components';
// import reset from 'styled-reset';

const GlobalStyle = createGlobalStyle`
  :root{
    --login-btn-before:hsl(206,96%,90%);
    --login-btn-after:hsl(206,93%,83.5%);
    --login-btn-border: hsl(205,47%,42%);;

    --signup-btn-before:hsl(206,100%,52%);
    --signup-btn-after:hsl(206,100%,40%);
    --menu-hover-background:hsl(210,8%,90%);
}
`;

export default GlobalStyle;
